package com.example.fragmentresult

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import com.example.fragmentresult.databinding.FragmentAuthBinding

class AuthFragment : Fragment() {

    private var binding: FragmentAuthBinding? = null
    private var customer: PersonModel? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentAuthBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        parentFragmentManager.setFragmentResultListener("data", viewLifecycleOwner) { key, bundle ->
            val login = bundle.getString("login")
            val password = bundle.getString("password")
            val name = bundle.getString("name")
            val surname = bundle.getString("surname")
            val email = bundle.getString("email")
            val gender = bundle.getString("gender")

            binding?.edLogin?.setText(login)
            binding?.edPassword?.setText(password)
            listOfCustomers.add(
                PersonModel(
                    name.toString(),
                    surname.toString(),
                    email.toString(),
                    login.toString(),
                    password.toString(),
                    gender.toString()
                )
            )
        }

        binding?.btLogIn?.setOnClickListener {
            for (i in listOfCustomers) {
                if (i.login == binding?.edLogin?.text.toString()) {
                    customer = i
                }
            }
            if (customer?.password == binding?.edPassword?.text.toString()) {
                val bundle = bundleOf(
                    "name" to customer?.name,
                    "surname" to customer?.surname,
                    "email" to customer?.email,
                    "gender" to customer?.gender
                )
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, DataFragment::class.java, bundle)
                    .commit()

                binding?.edLogin?.setTextColor(Color.BLACK)
                binding?.edPassword?.setTextColor(Color.BLACK)
                binding?.tvError?.isVisible = false

            } else {
                binding?.edLogin?.setTextColor(Color.RED)
                binding?.edPassword?.setTextColor(Color.RED)
                binding?.tvError?.isVisible = true
            }


        }

        binding?.btSignUp?.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .addToBackStack(AuthFragment::class.java.canonicalName)
                .replace(R.id.fragmentContainer, SignFragment())
                .commit()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
        
    }
}

private val listOfCustomers = mutableListOf(
    PersonModel(
        "nurlan",
        "veliev",
        "nurvel",
        "blacker",
        "1234",
        "male"
    )
)