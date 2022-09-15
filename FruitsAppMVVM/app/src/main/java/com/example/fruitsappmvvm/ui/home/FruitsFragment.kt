package com.example.fruitsappmvvm.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.fruitsappmvvm.databinding.FragmentFruitsBinding
import com.example.fruitsappmvvm.utils.BaseFragment
import com.example.fruitsappmvvm.utils.UIState

private const val TAG = "FruitsFragment"

class FruitsFragment : BaseFragment() {

    private var _binding: FragmentFruitsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFruitsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        fruitsViewModel.fruits.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UIState.LOADING -> {
                    Toast.makeText(requireContext(), "LOADING...", Toast.LENGTH_LONG).show()
                }
                is UIState.SUCCESS -> {
                    Toast.makeText(requireContext(), state.fruits.lastOrNull()?.name, Toast.LENGTH_LONG).show()
                }
                is UIState.FAILURE -> {
                    Log.e(TAG, "onCreateView: ${state.error.localizedMessage}", state.error)
                    Toast.makeText(requireContext(), state.error.localizedMessage, Toast.LENGTH_LONG).show()
                }
                is UIState.UPDATED -> {
                    // no-op
                }
            }
        }

        return root
    }

    override fun onStop() {
        super.onStop()
        fruitsViewModel.fruits.removeObservers(viewLifecycleOwner)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}