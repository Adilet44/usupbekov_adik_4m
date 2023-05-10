package com.example.usupbekov_adik_4m.ui.auth.accept

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.usupbekov_adik_4m.R
import com.example.usupbekov_adik_4m.databinding.FragmentAcceptCodeBinding
import com.example.usupbekov_adik_4m.utils.showToast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthProvider

class AcceptCodeFragment : Fragment() {

    private lateinit var binding: FragmentAcceptCodeBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentAcceptCodeBinding.inflate(inflater , container , false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val verId = arguments?.getString("verId")
        auth = FirebaseAuth.getInstance()
        binding. btnAC.setOnClickListener {
            acceptCode(verId)
        }
    }

    private fun acceptCode(verId: String?) {
        val code = binding.etCode.text.toString()
        val credential = PhoneAuthProvider.getCredential(verId!!, code)
        auth.signInWithCredential(credential).addOnSuccessListener {
            findNavController().navigate(R.id.navigation_home)
        }.addOnFailureListener {
            showToast(it.message.toString())
        }
    }
}