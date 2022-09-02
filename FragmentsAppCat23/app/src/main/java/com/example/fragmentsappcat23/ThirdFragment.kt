package com.example.fragmentsappcat23

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fragmentsappcat23.databinding.FragmentFirstBinding
import com.example.fragmentsappcat23.databinding.FragmentThirdBinding


class ThirdFragment : Fragment() {

    private val binding by lazy {
        FragmentThirdBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return binding.root
    }

    fun receivingData(data: String) {
        binding.textToReplace.text = data
    }
}