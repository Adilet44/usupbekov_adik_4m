package com.example.usupbekov_adik_4m.ui.onBoard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.usupbekov_adik_4m.remote.Pref
import com.example.usupbekov_adik_4m.databinding.FragmentOnBoardingBinding
import com.example.usupbekov_adik_4m.model.onBoard
import com.example.usupbekov_adik_4m.ui.onBoard.adapter.OnBoardingAdapter
import me.relex.circleindicator.CircleIndicator3

class onBoardingFragment : Fragment() {
    private lateinit var binding: FragmentOnBoardingBinding
    private lateinit var pref: Pref
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnBoardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pref= Pref(requireContext())
        val adapter = OnBoardingAdapter(this::onClick)
        binding.viewPager.adapter = adapter
        setIndicator()
    }

    private fun setIndicator() {
    val indicator : CircleIndicator3 = binding.indicator
        val viewPager = binding.viewPager
        indicator.setViewPager(viewPager)
    }

    private fun onClick(onBoard: onBoard) {
        findNavController().navigateUp()
        pref.saveUserSeen()
    }
}