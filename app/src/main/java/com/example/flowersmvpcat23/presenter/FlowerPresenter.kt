package com.example.flowersmvpcat23.presenter

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import com.example.flowersmvpcat23.model.domain.FlowerDomain
import com.example.flowersmvpcat23.model.domain.mapToFlowerDomain
import com.example.flowersmvpcat23.rest.FlowersAPI
import com.example.flowersmvpcat23.rest.Service
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

private const val TAG = "FlowerPresenter"

class FlowerPresenter(
    private val serviceApi: FlowersAPI = Service.flowersService,
    private val disposables: CompositeDisposable = CompositeDisposable()
) : FlowerPresenterContract {

    private var flowersViewContract: ViewContract? = null

    override fun initialisePresenter(viewContract: ViewContract) {
        flowersViewContract = viewContract
        Log.d(TAG, "initialisePresenter: Presenter gets initialised $viewContract")
    }

    override fun checkNetworkConnection(connectivityManager: ConnectivityManager?) {
        connectivityManager?.getNetworkCapabilities(connectivityManager.activeNetwork)?.let {
            flowersViewContract?.isNetworkAvailable(
                it.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            )
        } ?: flowersViewContract?.isNetworkAvailable(false)
    }

    override fun retrieveFlowers() {
        serviceApi.getAllFlowers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { flowers -> flowersViewContract?.onSuccess(flowers.mapToFlowerDomain()) },
                { error -> flowersViewContract?.onError(error) }
            )
            .also { disposables.add(it) }
    }

    override fun destroyPresenter() {
        flowersViewContract = null
        disposables.clear()
        Log.d(TAG, "destroyPresenter: Presenter destroyed $flowersViewContract")
    }
}

/**
 * This interface will be the way the VIEW will pass actions to the PRESENTER
 *
 * It has to be implemented in the [FlowerPresenter] class
 */
interface FlowerPresenterContract {
    /**
     * You need to have an initialised method to pass the [ViewContract]
     */
    fun initialisePresenter(viewContract: ViewContract)
    fun checkNetworkConnection(connectivityManager: ConnectivityManager?)
    fun retrieveFlowers()

    /**
     * You need to have a destroy method to avoid memory leaks when you have a rotation
     */
    fun destroyPresenter()
}

/**
 * This is the interface contract between the VIEW and the PRESENTER
 *
 * This is the way you can update the UI from the presenter
 * after an update on the data
 *
 * You need your view to implement this interface
 */
interface ViewContract {
    fun onLoading(isLoading: Boolean)
    fun onSuccess(flowers: List<FlowerDomain>)
    fun onError(error: Throwable)
    fun isNetworkAvailable(isAvailable: Boolean)
}