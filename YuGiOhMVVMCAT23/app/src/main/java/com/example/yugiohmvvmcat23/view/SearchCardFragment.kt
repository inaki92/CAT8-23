package com.example.yugiohmvvmcat23.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import com.example.yugiohmvvmcat23.R
import com.example.yugiohmvvmcat23.databinding.FragmentSearchCardBinding
import com.example.yugiohmvvmcat23.utils.CardType
import com.example.yugiohmvvmcat23.utils.CardsViewModelFactory
import com.example.yugiohmvvmcat23.utils.UIState
import com.example.yugiohmvvmcat23.viewmodel.CardsViewModel
import javax.inject.Inject

class SearchCardFragment : Fragment() {

    private val binding by lazy {
        FragmentSearchCardBinding.inflate(layoutInflater)
    }

    @Inject
    lateinit var cardsViewModelFactory: CardsViewModelFactory

    private val cardsViewModel: CardsViewModel by lazy {
        ViewModelProvider(requireActivity(), cardsViewModelFactory)[CardsViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding.searchCardValue.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                cardsViewModel.getCardByName(newText!!)
                return false
            }
        })

        cardsViewModel.spellCards.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UIState.LOADING -> {
                    // binding.loadingSpinner.visibility = View.VISIBLE
                    binding.searchCardsRv.visibility = View.GONE
                }
                is UIState.SUCCESS -> {
                    // binding.loadingSpinner.visibility = View.GONE
                    binding.searchCardsRv.visibility = View.VISIBLE

                    // cardsAdapter.updateCards(state.cards)
                }
                is UIState.ERROR -> {
                    // binding.loadingSpinner.visibility = View.GONE
                    binding.searchCardsRv.visibility = View.GONE

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

        cardsViewModel.getCardByName("")

        return binding.root
    }
}