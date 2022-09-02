package com.example.fragmentsappcat23

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fragmentsappcat23.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {

    private val binding by lazy {
        FragmentFirstBinding.inflate(layoutInflater)
    }

    private lateinit var communicator: Communicator

    override fun onAttach(context: Context) {
        super.onAttach(context)
        communicator = context as Communicator
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding.sendBtn.setOnClickListener {
            communicator.sendData(binding.nameEt.text.toString())
            communicator.sendDataToSecondFragment(binding.nameEt.text.toString() + " This is second fragment")
        }

        return binding.root
    }
}