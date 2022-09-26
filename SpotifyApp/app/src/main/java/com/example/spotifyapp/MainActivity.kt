package com.example.spotifyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spotifyapp.adapter.SpotifyAdapter
import com.example.spotifyapp.databinding.ActivityMainBinding
import com.example.spotifyapp.utils.SongGenres
import com.example.spotifyapp.utils.UIState
import com.example.spotifyapp.utils.ViewIntent
import com.example.spotifyapp.viewmodel.SpotifyViewModel
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "MainActivity"

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel by lazy {
        ViewModelProvider(this)[SpotifyViewModel::class.java]
    }

    private val artistAdapter by lazy {
        SpotifyAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.itemsRecycler.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = artistAdapter
        }

        viewModel.artists.observe(this) {
            when (it) {
                is UIState.LOADING -> {}
                is UIState.SUCCESS -> {
                    artistAdapter.updateArtists(it.data)
                }
                is UIState.ERROR -> {
                    Toast.makeText(baseContext, it.error.localizedMessage, Toast.LENGTH_LONG).show()
                }
            }
        }

//        viewModel.getIntentActionView(
//            ViewIntent.SearchArtistsByIds(
//                listOf(
//                    "2CIMQHirSU0MQqyYHq0eOx",
//                    "57dN52uHvrHOxijzpIgu3E",
//                    "1vCWHaC5f2uS3yhpwWbIA6")
//            )
//        )

        viewModel.getIntentActionView(
            ViewIntent.SearchArtistsByGenre(
                SongGenres.ROCK
            )
        )
    }
}