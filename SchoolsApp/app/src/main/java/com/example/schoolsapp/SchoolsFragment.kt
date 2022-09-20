package com.example.schoolsapp

import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.schoolsapp.adapter.SchoolsAdapter
import com.example.schoolsapp.databinding.FragmentSchoolsBinding
import com.example.schoolsapp.model.domain.School
import com.example.schoolsapp.utils.BaseFragment
import com.example.schoolsapp.utils.ClickHandler
import com.example.schoolsapp.utils.UIState
import com.example.schoolsapp.viewmodel.SchoolsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SchoolsFragment : BaseFragment() {

    private val binding by lazy {
        FragmentSchoolsBinding.inflate(layoutInflater)
    }

    private val schoolAdapter by lazy {
        SchoolsAdapter { handler ->
            when(handler) {
                is ClickHandler.WebsiteClick -> {
                    Uri.parse("https://${handler.website}").also { uri ->
                        startActivity(Intent(ACTION_VIEW, uri))
                    }
                }
                is ClickHandler.AddressClick -> {
                    Uri.parse("geo:0,0?q=${handler.latitude}, ${handler.longitude}").also { location ->
                        activity?.startActivity(Intent(ACTION_VIEW, location))
                    }
                }
                is ClickHandler.DetailsClick -> {
                    schoolViewModel.school = handler.school
                    findNavController().navigate(
                        R.id.action_SchoolsFragment_to_DetailsFragment
                    )
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding.schoolsRv.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = schoolAdapter
        }

        schoolViewModel.schools.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UIState.LOADING -> {
                    binding.schoolsRv.visibility = View.GONE
                    binding.loadingSchools.visibility = View.VISIBLE
                }
                is UIState.SUCCESS<*> -> {
                    binding.schoolsRv.visibility = View.VISIBLE
                    binding.loadingSchools.visibility = View.GONE

                    val newSchools = state.data as List<School>
                    schoolAdapter.updateSchools(newSchools)
                }
                is UIState.ERROR -> {
                    binding.schoolsRv.visibility = View.GONE
                    binding.loadingSchools.visibility = View.GONE
                }
            }
        }

        return binding.root
    }
}