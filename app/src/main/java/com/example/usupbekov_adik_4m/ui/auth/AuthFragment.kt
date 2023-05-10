package com.example.usupbekov_adik_4m.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.usupbekov_adik_4m.R
import com.example.usupbekov_adik_4m.databinding.FragmentAuthBinding

class AuthFragment : Fragment() {

    private lateinit var binding: FragmentAuthBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAuthBinding.inflate(inflater , container , false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        authToVerify()
        authToHome()
    }

    private fun authToHome( ) {
        binding.btnGoogle.setOnClickListener {
            findNavController().navigate(R.id.navigation_home)
        }
    }

    private fun authToVerify() {
        binding.btnNumber.setOnClickListener {
            findNavController().navigate(R.id.verifyFragment)
        }
    }

}