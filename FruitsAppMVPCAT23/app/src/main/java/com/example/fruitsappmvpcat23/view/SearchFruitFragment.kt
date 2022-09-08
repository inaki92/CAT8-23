package com.example.fruitsappmvpcat23.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.fruitsappmvpcat23.R
import com.example.fruitsappmvpcat23.databinding.FragmentSearchFruitBinding

class SearchFruitFragment : Fragment() {

    private val binding by lazy {
        FragmentSearchFruitBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding.searchFruitView.setOnSearchClickListener {
            // here you do the network call

        }

        return binding.root
    }
}