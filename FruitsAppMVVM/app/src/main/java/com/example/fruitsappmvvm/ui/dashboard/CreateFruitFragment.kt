package com.example.fruitsappmvvm.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.fruitsappmvvm.databinding.FragmentCreateFruitBinding
import com.example.fruitsappmvvm.model.Fruit
import com.example.fruitsappmvvm.model.Nutritions
import com.example.fruitsappmvvm.utils.BaseFragment
import com.example.fruitsappmvvm.utils.UIState

private const val TAG = "CreateFruitFragment"

class CreateFruitFragment : BaseFragment() {

    private var _binding: FragmentCreateFruitBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCreateFruitBinding.inflate(inflater, container, false)
        val root: View = binding.root

        fruitsViewModel.fruitCreated.observe(viewLifecycleOwner) {
            when (it) {
                is UIState.UPDATED -> {
                    Toast.makeText(requireContext(), "fruit created successfully", Toast.LENGTH_LONG).show()
                }
                is UIState.FAILURE -> {
                    Log.e(TAG, "onCreateView: ${it.error.localizedMessage}", it.error)
                    Toast.makeText(requireContext(), it.error.localizedMessage, Toast.LENGTH_LONG).show()
                }
                else -> {
                    // no-op
                }
            }
        }

        binding.saveFruit.setOnClickListener {
            fruitsViewModel.createFruit(
                Fruit(
                    genus = "something",
                    name = "apple new",
                    family = "apple family",
                    order = "any order",
                    nutritions = Nutritions(
                        carbohydrates = 3.3,
                        protein = 2.3,
                        fat = 5.5,
                        calories = 123,
                        sugar = 23.43
                    )
                )
            )
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}