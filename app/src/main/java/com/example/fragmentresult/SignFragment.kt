package com.example.fragmentresult

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import com.example.fragmentresult.databinding.FragmentSignBinding

class SignFragment : Fragment() {
    private var binding: FragmentSignBinding? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSignBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.SignUp?.setOnClickListener {

            val name = binding?.etName?.text.toString()
            val surname = binding?.edSurName?.text.toString()
            val login = binding?.edLogin?.text.toString()
            val password = binding?.edPassword?.text.toString()
            val email = binding?.edEmail?.text.toString()
            var gender: String? = null

            when (binding?.radioGroup?.checkedRadioButtonId) {
                binding?.male?.id -> {
                    gender = "Male"
                }

                binding?.Female?.id -> {
                    gender = "Female"
                }

                else -> {}
            }

            if (name.isEmpty()
                || surname.isEmpty()
                || login.isEmpty()
                || password.isEmpty()
                || email.isEmpty()
                || gender.isNullOrEmpty()
            ) {
                binding?.tvError?.isVisible = true
            } else {
                binding?.tvError?.text = "Ваш Аккаунт создан успешно!"
                binding?.tvError?.setTextColor(Color.GREEN)
                Toast.makeText(context, "Ваш Аккаунт создан успешно!", Toast.LENGTH_SHORT).show()

                parentFragmentManager.setFragmentResult(
                    "data",
                    bundleOf(
                        "login" to login,
                        "password" to password,
                        "name" to name,
                        "surname" to surname,
                        "email" to email,
                        "gender" to gender
                    )
                )
                parentFragmentManager.popBackStack()
            }
        }
    }
}