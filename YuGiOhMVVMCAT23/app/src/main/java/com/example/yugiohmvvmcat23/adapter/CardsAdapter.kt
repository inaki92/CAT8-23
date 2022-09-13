package com.example.yugiohmvvmcat23.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.yugiohmvvmcat23.R
import com.example.yugiohmvvmcat23.databinding.CardItemBinding
import com.example.yugiohmvvmcat23.databinding.LetterItemBinding
import com.example.yugiohmvvmcat23.model.Data
import com.example.yugiohmvvmcat23.model.domain.DomainCard
import com.example.yugiohmvvmcat23.utils.ItemViewType
import com.squareup.picasso.Picasso

class CardsAdapter(
    private val cardsData: MutableList<ItemViewType> = mutableListOf()
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    fun updateCards(newCards: List<DomainCard>) {
        cardsData.clear()

        var firstLetter = '0'

        newCards.sortedBy { it.cardName }.forEach {
            if (!it.cardName.startsWith(firstLetter)) {
                cardsData.add(ItemViewType.HeaderItem(it.cardName.first().toString()))
                firstLetter = it.cardName.first()
            }
            cardsData.add(ItemViewType.CardItem(it))
        }

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            0 -> CardViewHolder(
                CardItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            else -> LettersViewHolder(
                LetterItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        when(val data = cardsData[position]) {
            is ItemViewType.HeaderItem -> (holder as LettersViewHolder).bind(data.letter)
            is ItemViewType.CardItem -> (holder as CardViewHolder).bind(data.card)
        }

    override fun getItemCount(): Int = cardsData.size

    override fun getItemViewType(position: Int): Int {
        return when (cardsData[position]) {
            is ItemViewType.HeaderItem -> 1
            is ItemViewType.CardItem -> 0
        }
    }
}

class CardViewHolder(
    private val binding: CardItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(data: DomainCard) {
        binding.cardName.text = data.cardName
        binding.cardPrice.text = data.cardMarketPrice
        binding.cardRace.text = data.cardRace
        binding.cardType.text = data.cardType

        Picasso.get()
            .load(data.cardImage)
            .placeholder(R.drawable.ic_baseline_image_search_24)
            .error(R.drawable.ic_baseline_broken_image_24)
            .into(binding.cardImage)
    }
}

class LettersViewHolder(
    private val binding: LetterItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(letter: String) {
        binding.letterValue.text = letter
    }
}