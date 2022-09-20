package com.example.schoolsapp.utils

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.schoolsapp.viewmodel.SchoolsViewModel

open class BaseFragment : Fragment() {

    protected val schoolViewModel by lazy {
        ViewModelProvider(requireActivity())[SchoolsViewModel::class.java]
    }
}