package com.example.schoolsapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.schoolsapp.model.domain.School

class SchoolDetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val school = arguments?.getParcelable("SCHOOL_DATA") as? School


        return inflater.inflate(R.layout.fragment_school_details, container, false)
    }
}