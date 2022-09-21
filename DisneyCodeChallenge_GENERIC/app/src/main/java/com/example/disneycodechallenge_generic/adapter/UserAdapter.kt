package com.example.disneycodechallenge_generic.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.disneycodechallenge_generic.databinding.HeaderItemBinding
import com.example.disneycodechallenge_generic.databinding.UserItemBinding
import com.example.disneycodechallenge_generic.model.domain.DomainUser
import com.example.disneycodechallenge_generic.utils.ItemView

class UserAdapter(
    private val viewItems: MutableList<ItemView> = mutableListOf()
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            0 -> HeaderViewHolder(
                HeaderItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            else -> UserViewHolder(
                UserItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        when (val item = viewItems[position]) {
            is ItemView.HeaderItem -> (holder as HeaderViewHolder).bind(item.header)
            is ItemView.UserItem -> (holder as UserViewHolder).bind(item.user)
        }

    override fun getItemCount(): Int =
        viewItems.size

    override fun getItemViewType(position: Int): Int {
        return when (viewItems[position]) {
            is ItemView.HeaderItem -> 0
            is ItemView.UserItem -> 1
        }
    }
}

class HeaderViewHolder(
    private val binding: HeaderItemBinding
): RecyclerView.ViewHolder(binding.root) {

    fun bind(header: String) {

    }
}

class UserViewHolder(
    private val binding: UserItemBinding
): RecyclerView.ViewHolder(binding.root) {

    fun bind(user: DomainUser) {

    }
}