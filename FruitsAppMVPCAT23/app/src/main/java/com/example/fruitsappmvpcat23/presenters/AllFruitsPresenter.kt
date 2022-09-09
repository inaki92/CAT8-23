package com.example.fruitsappmvpcat23.presenters

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.example.fruitsappmvpcat23.database.FruitsDAO
import com.example.fruitsappmvpcat23.database.LocalFruitsRepository
import com.example.fruitsappmvpcat23.database.LocalFruitsRepositoryImpl
import com.example.fruitsappmvpcat23.model.domain.FruitDomain
import com.example.fruitsappmvpcat23.rest.FruitsRepository
import com.example.fruitsappmvpcat23.rest.FruitsRepositoryImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

interface AllFruitsPresenter {
    fun init(viewContract: ViewContractAllFruits)
    fun checkNetworkConnection(connectivityManager: ConnectivityManager)
    fun getAllFruits()
    fun destroy()
}

class AllFruitsPresenterImpl @Inject constructor(
    private val disposables: CompositeDisposable = CompositeDisposable(),
    private val repository: FruitsRepository
) : AllFruitsPresenter {

    private var contractAllFruits: ViewContractAllFruits? = null

    override fun init(viewContract: ViewContractAllFruits) {
        contractAllFruits = viewContract
    }

    override fun checkNetworkConnection(connectivityManager: ConnectivityManager) {
        connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)?.let {
            if (!it.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)) {
                contractAllFruits?.onFailure(Throwable("No internet connection"))
            }
        }
    }

    override fun getAllFruits() {
        contractAllFruits?.loadingFruits(true)

        repository.getAllFruits()
            .subscribe(
                { fruits -> contractAllFruits?.onSuccess(fruits) },
                { error -> contractAllFruits?.onFailure(error) }
            )
            .also { disposables.add(it) }
    }

    override fun destroy() {
        disposables.clear()
        contractAllFruits = null
    }

}

interface ViewContractAllFruits {
    fun loadingFruits(isLoading: Boolean)
    fun onSuccess(fruits: List<FruitDomain>)
    fun onFailure(error: Throwable)
}

