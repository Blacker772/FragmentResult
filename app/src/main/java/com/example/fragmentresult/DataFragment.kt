package com.example.fragmentresult

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fragmentresult.databinding.FragmentDataBinding

class DataFragment : Fragment() {
    private var binding: FragmentDataBinding? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentDataBinding.inflate(layoutInflater,container,false)
        return binding?.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val name = arguments?.getString("name")
        val surname = arguments?.getString("surname")
        val email = arguments?.getString("email")
        val gender = arguments?.getString("gender")

        binding?.tvName?.text = name
        binding?.tvSurName?.text = surname
        binding?.tvEmail?.text = email
        binding?.tvGender?.text = gender
    }
}