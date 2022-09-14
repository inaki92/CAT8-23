package com.example.musicexampleapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.musicexampleapp.databinding.FragmentFirstBinding
import com.example.musicexampleapp.rest.MusicApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */

private const val TAG = "FirstFragment"

class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)

//        lifecycleScope.launch(Dispatchers.IO) {
//            try {
//                val response = MusicApi.serviceApi.getMusic()
//                Log.d(TAG, "onCreateView: ${response.body()?.songs?.firstOrNull()?.artistName}")
//            } catch (e: Exception) {
//                Log.e(TAG, "onCreateView: ${e.localizedMessage}", e)
//            }
//
//        }

        MusicApi.serviceApi.getMusic()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Log.d(TAG, "onCreateView: ${it.songs}")
                    Log.d(TAG, "onCreateView: ${it.songs?.firstOrNull()?.artistName}")
                },
                { Log.e(TAG, "onCreateView: ${it.localizedMessage}", it) }
            )

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}