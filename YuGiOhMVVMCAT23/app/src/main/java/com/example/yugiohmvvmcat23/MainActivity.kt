package com.example.yugiohmvvmcat23

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.yugiohmvvmcat23.databinding.ActivityMainBinding
import com.example.yugiohmvvmcat23.di.YuGiOhApp

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        YuGiOhApp.yuGiOhComponent.inject(this)

        val navController = findNavController(R.id.frag_container_cards)
        binding.cardsNav.setupWithNavController(navController)
    }
}