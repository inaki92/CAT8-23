package com.example.flowersmvpcat23.view

import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.flowersmvpcat23.R
import com.example.flowersmvpcat23.adapter.FlowerAdapter
import com.example.flowersmvpcat23.databinding.FragmentFlowersBinding
import com.example.flowersmvpcat23.model.domain.FlowerDomain
import com.example.flowersmvpcat23.presenter.FlowerPresenter
import com.example.flowersmvpcat23.presenter.FlowerPresenterContract
import com.example.flowersmvpcat23.presenter.ViewContract
import kotlinx.coroutines.flow.flow

private const val TAG = "FlowersFragment"

class FlowersFragment : Fragment(), ViewContract {

    private val flowerPresenter: FlowerPresenterContract by lazy {
        FlowerPresenter()
    }

    private val binding by lazy {
        FragmentFlowersBinding.inflate(layoutInflater)
    }

    private val flowersAdapter by lazy {
        FlowerAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        flowerPresenter.initialisePresenter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        flowerPresenter.checkNetworkConnection(
            getSystemService(requireContext(), ConnectivityManager::class.java)
        )

        binding.flowerRv.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = flowersAdapter
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()

        flowerPresenter.retrieveFlowers()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.apply {
            putParcelable("RECYCLER_STATE", binding.flowerRv.layoutManager?.onSaveInstanceState())
        }
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        binding.flowerRv.layoutManager?.onRestoreInstanceState(savedInstanceState?.getParcelable("RECYCLER_STATE"))
    }

    override fun onStop() {
        super.onStop()
        flowerPresenter.destroyPresenter()
    }

    override fun onLoading(isLoading: Boolean) {
        TODO("Not yet implemented")
    }

    override fun onSuccess(flowers: List<FlowerDomain>) {
        flowersAdapter.updateFlowers(flowers)
        Log.d(TAG, "onSuccess: RecyclerView updated in onSuccess $flowers")
    }

    override fun onError(error: Throwable) {
        AlertDialog.Builder(requireActivity())
            .setTitle("ERROR OCCURRED")
            .setMessage(error.localizedMessage)
            .setNegativeButton("DISMISS") { dialog, _ ->
                dialog.dismiss()
            }
    }

    override fun isNetworkAvailable(isAvailable: Boolean) {
        if (!isAvailable) {
            AlertDialog.Builder(requireActivity())
                .setTitle("ERROR OCCURRED")
                .setMessage("No internet connection found")
                .setNegativeButton("DISMISS") { dialog, _ ->
                    dialog.dismiss()
                }
        }
    }
}