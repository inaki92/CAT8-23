package com.example.spotifyapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.spotifyapp.databinding.ArtistItemBinding
import com.example.spotifyapp.databinding.HeaderArtistNameBinding
import com.example.spotifyapp.model.domain.DomainArtist
import com.example.spotifyapp.utils.ItemView

class SpotifyAdapter(
    private val dataSet: MutableList<ItemView> = mutableListOf()
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    fun updateArtists(newArtists: List<DomainArtist>) {
        newArtists.sortedBy { it.name.lowercase() }.forEach {
            dataSet.add(ItemView.HeaderName(it.name))
            dataSet.add(ItemView.ArtistItem(it))
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            0 ->
                HeaderArtistNameViewHolder(
                    HeaderArtistNameBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            else ->
                ArtistViewHolder(
                    ArtistItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        when (val item = dataSet[position]) {
            is ItemView.HeaderName -> (holder as HeaderArtistNameViewHolder).bind(item.artistName)
            is ItemView.ArtistItem -> (holder as ArtistViewHolder).bind(item.artist)
        }

    override fun getItemCount(): Int = dataSet.size

    override fun getItemViewType(position: Int): Int {
        return when (dataSet[position]) {
            is ItemView.HeaderName -> 0
            is ItemView.ArtistItem -> 1
        }
    }
}

class ArtistViewHolder(
    private val binding: ArtistItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(artist: DomainArtist) {
        binding.artistName.text = artist.name
        binding.artistGenres.text = artist.genres.joinToString(",")
    }
}

class HeaderArtistNameViewHolder(
    private val binding: HeaderArtistNameBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(artistName: String) {
        binding.artistHeaderName.text = artistName
    }
}