package com.example.yugiohmvvmcat23.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.yugiohmvvmcat23.adapter.CardsAdapter
import com.example.yugiohmvvmcat23.databinding.FragmentSpellCardsBinding
import com.example.yugiohmvvmcat23.di.YuGiOhApp
import com.example.yugiohmvvmcat23.rest.YuGiOhRepository
import com.example.yugiohmvvmcat23.utils.CardType
import com.example.yugiohmvvmcat23.utils.CardsViewModelFactory
import com.example.yugiohmvvmcat23.utils.UIState
import com.example.yugiohmvvmcat23.viewmodel.CardsViewModel
import javax.inject.Inject

class SpellCardsFragment : Fragment() {

    private val binding by lazy {
        FragmentSpellCardsBinding.inflate(layoutInflater)
    }

    private val cardsAdapter by lazy {
        CardsAdapter()
    }

    @Inject
    lateinit var cardsViewModelFactory: CardsViewModelFactory

    // This approach would be the same as passing fragment as the store owner
    // private val fragViewModel: CardsViewModel by viewModels()

    // This approach would be the same as passing activity as the store owner
    // private val activityViewModel: CardsViewModel by activityViewModels()

    // Creating viewModel factory
    // TODO inject factory using DI
    private val cardsViewModel: CardsViewModel by lazy {
        ViewModelProvider(requireActivity(), cardsViewModelFactory)[CardsViewModel::class.java]
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        // here you are calling Dagger component to inject the dependencies
        YuGiOhApp.yuGiOhComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding.spellCardsRv.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = cardsAdapter
        }

        cardsViewModel.spellCards.observe(viewLifecycleOwner)  { state ->
            when (state) {
                is UIState.LOADING -> {
                    binding.loadingSpinner.visibility = View.VISIBLE
                    binding.spellCardsRv.visibility = View.GONE
                }
                is UIState.SUCCESS -> {
                    binding.loadingSpinner.visibility = View.GONE
                    binding.spellCardsRv.visibility = View.VISIBLE

                    cardsAdapter.updateCards(state.cards)
                }
                is UIState.ERROR -> {
                    binding.loadingSpinner.visibility = View.GONE
                    binding.spellCardsRv.visibility = View.GONE

                    AlertDialog.Builder(requireActivity())
                        .setTitle("Error occurred")
                        .setMessage(state.error.localizedMessage)
                        .setNegativeButton("DISMISS") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .setPositiveButton("Retry") { dialog, _ ->
                            cardsViewModel.getCardsByType(CardType.SPELL_CARD)
                            dialog.dismiss()
                        }
                        .create()
                        .show()
                }
            }
        }

        cardsViewModel.getCardsByType(CardType.SPELL_CARD)

        return binding.root
    }
}