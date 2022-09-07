package com.example.flowersmvpcat23.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.flowersmvpcat23.R
import com.example.flowersmvpcat23.databinding.FlowerItemBinding
import com.example.flowersmvpcat23.model.domain.FlowerDomain
import com.example.flowersmvpcat23.rest.FlowersAPI

class FlowerAdapter(
    private val flowersDataset: MutableList<FlowerDomain> = mutableListOf()
) : RecyclerView.Adapter<FlowerViewHolder>() {

    fun updateFlowers(newFlowers: List<FlowerDomain>) {
        flowersDataset.clear()
        flowersDataset.addAll(newFlowers)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlowerViewHolder =
        FlowerViewHolder(
            FlowerItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: FlowerViewHolder, position: Int) =
        holder.bind(flowersDataset[position])

    override fun getItemCount(): Int = flowersDataset.size
}

class FlowerViewHolder(
    private val binding: FlowerItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(flower: FlowerDomain) {
        binding.nameFlower.text = flower.flowerName
        binding.categoryFlower.text = flower.flowerCategory
        binding.priceFlower.text = flower.flowerPrice.toString()

        Glide.with(binding.root)
            .load(FlowersAPI.IMAGE_PATH + flower.flowerPhoto)
            .placeholder(R.drawable.ic_baseline_image_search_24)
            .error(R.drawable.ic_baseline_broken_image_24)
            .circleCrop()
            .into(binding.photoFlower)
    }
}

