package com.example.fragmentsappcat23

import android.content.Context
import android.os.Bundle
import android.os.Message
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fragmentsappcat23.databinding.FragmentFirstBinding
import com.example.fragmentsappcat23.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {

    private val binding by lazy {
        FragmentSecondBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return binding.root
    }

    fun receiveData(message: String) {
        binding.messageTv.text = message
    }


}