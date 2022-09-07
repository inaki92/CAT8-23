package com.example.fruitsappmvpcat23.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.fruitsappmvpcat23.R
import com.example.fruitsappmvpcat23.database.FruitsDatabase
import com.example.fruitsappmvpcat23.databinding.FragmentAllFruitsBinding
import com.example.fruitsappmvpcat23.model.domain.FruitDomain
import com.example.fruitsappmvpcat23.presenters.AllFruitsPresenter
import com.example.fruitsappmvpcat23.presenters.AllFruitsPresenterImpl
import com.example.fruitsappmvpcat23.presenters.ViewContractAllFruits

private const val TAG = "AllFruitsFragment"

class AllFruitsFragment : Fragment(), ViewContractAllFruits {

    private val binding by lazy {
        FragmentAllFruitsBinding.inflate(layoutInflater)
    }

    /**
     * This is the database object you need to access the DAO
     */
    private val fruitsDatabase by lazy {
        Room.databaseBuilder(
            requireActivity().applicationContext,
            FruitsDatabase::class.java,
            "fruits-db"
        ).build()
    }

    /**
     * This is the presenter object that your view will use to interact with the business logic
     */
    private val presenter: AllFruitsPresenter by lazy {
        AllFruitsPresenterImpl(fruitsDAO = fruitsDatabase.getFruitsDao())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.init(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        presenter.getAllFruits()

        return binding.root
    }

    override fun loadingFruits(isLoading: Boolean) {
        Toast.makeText(requireContext(), "This is loading: $isLoading", Toast.LENGTH_LONG).show()
        Log.d(TAG, "loadingFruits: $isLoading")
    }

    override fun onSuccess(fruits: List<FruitDomain>) {
        Toast.makeText(requireContext(), "Success: ${fruits.first().fruitName}", Toast.LENGTH_LONG).show()
        Log.d(TAG, "onSuccess: $fruits")
    }

    override fun onFailure(error: Throwable) {
        Toast.makeText(requireContext(), "FAILURE: ${error.localizedMessage}", Toast.LENGTH_LONG).show()
        Log.d(TAG, "onFailure: ${error.localizedMessage}")
    }
}