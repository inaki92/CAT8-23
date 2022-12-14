package com.example.fruitsappmvpcat23.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.fruitsappmvpcat23.adapter.AllFruitsAdapter
import com.example.fruitsappmvpcat23.database.FruitsDatabase
import com.example.fruitsappmvpcat23.database.MIGRATION_1_2
import com.example.fruitsappmvpcat23.databinding.FragmentFruitsBinding
import com.example.fruitsappmvpcat23.di.FruitsApp
import com.example.fruitsappmvpcat23.model.domain.FruitDomain
import com.example.fruitsappmvpcat23.presenters.AllFruitsPresenter
import com.example.fruitsappmvpcat23.presenters.ViewContractAllFruits
import javax.inject.Inject

private const val TAG = "AllFruitsFragment"

class AllFragment : Fragment(), ViewContractAllFruits {

    private val binding by lazy {
        FragmentFruitsBinding.inflate(layoutInflater)
    }

    /**
     * This is the database object you need to access the DAO
     */
    private val fruitsDatabase by lazy {
        Room.databaseBuilder(
            requireActivity().applicationContext,
            FruitsDatabase::class.java,
            "fruits-db"
        )
            .addMigrations(MIGRATION_1_2)
            .build()
    }

    /**
     * This is the presenter object that your view will use to interact with the business logic
     */
//    private val presenter: AllFruitsPresenter by lazy {
//        AllFruitsPresenterImpl()
//    }

    @Inject
    lateinit var presenter: AllFruitsPresenter

    private val fruitAdapter by lazy {
        AllFruitsAdapter()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        FruitsApp.fruitsComponent.inject(this)
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

        binding.fruitsRv.apply {
            layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = fruitAdapter
        }

        presenter.getAllFruits()

        binding.refreshItem.setOnRefreshListener {
            //  here is the action when you swipe to refresh
            presenter.getAllFruits()
            binding.refreshItem.isRefreshing = true
        }

        return binding.root
    }

    override fun loadingFruits(isLoading: Boolean) {
        Toast.makeText(requireContext(), "This is loading: $isLoading", Toast.LENGTH_LONG).show()
        Log.d(TAG, "loadingFruits: $isLoading")
    }

    override fun onSuccess(fruits: List<FruitDomain>) {
        Toast.makeText(requireContext(), "Success: ${fruits.first().fruitName}", Toast.LENGTH_LONG).show()
        Log.d(TAG, "onSuccess: $fruits")
        binding.refreshItem.isRefreshing = false
        fruitAdapter.updateFruits(fruits)
    }

    override fun onFailure(error: Throwable) {
        Toast.makeText(requireContext(), "FAILURE: ${error.localizedMessage}", Toast.LENGTH_LONG).show()
        Log.d(TAG, "onFailure: ${error.localizedMessage}")
        binding.refreshItem.isRefreshing = false
    }

    override fun gettingDta() {

    }

    override fun someOtherTHing() {
        TODO("Not yet implemented")
    }

    override fun somethingElse() {
        TODO("Not yet implemented")
    }

    override fun onStop() {
        super.onStop()
        retainInstance = true
    }
}