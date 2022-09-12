package com.example.yugiohmvvmcat23.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.yugiohmvvmcat23.R
import com.example.yugiohmvvmcat23.databinding.FragmentSpellCardsBinding
import com.example.yugiohmvvmcat23.utils.CardType
import com.example.yugiohmvvmcat23.utils.CardsViewModelFactory
import com.example.yugiohmvvmcat23.utils.UIState
import com.example.yugiohmvvmcat23.viewmodel.CardsViewModel

class SpellCardsFragment : Fragment() {

    private val binding by lazy {
        FragmentSpellCardsBinding.inflate(layoutInflater)
    }

    // This approach would be the same as passing fragment as the store owner
    // private val fragViewModel: CardsViewModel by viewModels()

    // This approach would be the same as passing activity as the store owner
    // private val activityViewModel: CardsViewModel by activityViewModels()

    // Creating viewModel factory
    // TODO inject factory using DI
    private val cardsViewModel: CardsViewModel by lazy {
        ViewModelProvider(requireActivity(), CardsViewModelFactory())[CardsViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        cardsViewModel.spellCards.observe(viewLifecycleOwner)  { state ->
            when (state) {
                is UIState.LOADING -> {}
                is UIState.SUCCESS -> {}
                is UIState.ERROR -> {}
            }
        }

        cardsViewModel.getCardsByType(CardType.SPELL_CARD)

        return binding.root
    }
}