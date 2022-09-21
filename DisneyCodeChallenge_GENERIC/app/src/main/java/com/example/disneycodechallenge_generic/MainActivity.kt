package com.example.disneycodechallenge_generic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.disneycodechallenge_generic.di.UserApp
import com.example.disneycodechallenge_generic.utils.UserViewModelFactory
import com.example.disneycodechallenge_generic.viewmodel.UsersViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: UserViewModelFactory

    private val userViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[UsersViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        UserApp.userComponent.inject(this)
    }
}