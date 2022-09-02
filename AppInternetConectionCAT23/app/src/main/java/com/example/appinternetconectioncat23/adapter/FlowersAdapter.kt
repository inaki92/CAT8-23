package com.example.appinternetconectioncat23.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appinternetconectioncat23.databinding.FlowerItemBinding
import com.example.appinternetconectioncat23.model.Flower

class FlowersAdapter(
    private val flowersDataSet: MutableList<Flower> = mutableListOf(),
    private val onFlowerClicked: (Flower) -> Unit
) : RecyclerView.Adapter<FlowerViewHolder>() {

    fun updateFlowers(newFlowers: List<Flower>) {
        flowersDataSet.clear()
        flowersDataSet.addAll(newFlowers)
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
        holder.bind(flowersDataSet[position], onFlowerClicked)

    override fun getItemCount(): Int = flowersDataSet.size
}

class FlowerViewHolder(
    private val binding: FlowerItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(flower: Flower, onFlowerClicked: (Flower) -> Unit) {
        binding.flowerName.text = flower.flowerName
        binding.flowerCategory.text = flower.category
        binding.flowerPrice.text = flower.price.toString()

        binding.flowerImage.setOnClickListener {
            onFlowerClicked(flower)
        }

        // TODO load the image
    }

}