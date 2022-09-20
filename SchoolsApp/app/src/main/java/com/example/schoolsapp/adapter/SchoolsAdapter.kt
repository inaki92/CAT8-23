package com.example.schoolsapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.schoolsapp.databinding.HeaderItemBinding
import com.example.schoolsapp.databinding.SchoolItemBinding
import com.example.schoolsapp.model.domain.School
import com.example.schoolsapp.utils.ClickHandler
import com.example.schoolsapp.utils.ItemView

class SchoolsAdapter(
    private val dataSet: MutableList<ItemView> = mutableListOf(),
    private val clicksHandler: (ClickHandler) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    fun updateSchools(newSchools: List<School>) {
        dataSet.clear()

        var initialChar = '0'
        newSchools.sortedBy { it.schoolName }.forEach {
            if (!it.schoolName.startsWith(initialChar)) {
                initialChar = it.schoolName.first()
                dataSet.add(ItemView.HeaderItem(initialChar.toString()))
            }
            dataSet.add(ItemView.SchoolItem(it))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when(viewType) {
            0 -> {
                HeaderViewHolder(
                    HeaderItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            else -> {
                SchoolViewHolder(
                    SchoolItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        when(val item = dataSet[position]) {
            is ItemView.HeaderItem -> (holder as HeaderViewHolder).bind(item.character)
            is ItemView.SchoolItem -> (holder as SchoolViewHolder).bind(item.school, clicksHandler)
        }

    override fun getItemCount(): Int = dataSet.size

    override fun getItemViewType(position: Int): Int {
        return when(dataSet[position]) {
            is ItemView.HeaderItem -> 0
            is ItemView.SchoolItem -> 1
        }
    }
}

class SchoolViewHolder(
    private val binding: SchoolItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(school: School, onClickHandler: (ClickHandler) -> Unit) {
        binding.schName.text = school.schoolName

        binding.schAddress.apply {
            text = school.schoolAddress
            setOnClickListener {
                onClickHandler(ClickHandler.AddressClick(school.schoolLongitude, school.schoolLatitude))
            }
        }

        binding.schWebsite.apply {
            text = school.schoolWebsite
            setOnClickListener {
                onClickHandler(ClickHandler.WebsiteClick(school.schoolWebsite))
            }
        }

        binding.schDetails.setOnClickListener {
            onClickHandler(ClickHandler.DetailsClick(school))
        }
    }
}

class HeaderViewHolder(
    private val binding: HeaderItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(letter: String) {
        binding.headerLetter.text = letter
    }
}