package com.example.fruitsappmvvm.utils

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.fruitsappmvvm.viewmodel.FruitsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
open class BaseFragment : Fragment() {

    protected val fruitsViewModel by lazy {
        ViewModelProvider(this)[FruitsViewModel::class.java]
    }
}