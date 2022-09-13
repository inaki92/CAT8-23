package com.example.fruitsappmvpcat23.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fruitsappmvpcat23.model.domain.FruitDomain

class AllFruitsAdapter(
     private val fruitsData: MutableList<FruitDomain> = mutableListOf()
) : RecyclerView.Adapter<FruitViewHolder>() {

     fun updateFruits(newFruits: List<FruitDomain>) {
          fruitsData.clear()
          fruitsData.addAll(newFruits)
          notifyDataSetChanged()
     }

     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FruitViewHolder {
          TODO("Not yet implemented")
     }

     override fun onBindViewHolder(holder: FruitViewHolder, position: Int) =
          holder.bind(fruitsData[position])

     override fun getItemCount(): Int = fruitsData.size
}

class FruitViewHolder() : RecyclerView.ViewHolder() {
     fun bind(fruit: FruitDomain) {

     }
}