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

interface AllFruitsPresenter {
    fun init(viewContract: ViewContractAllFruits)
    fun checkNetworkConnection(connectivityManager: ConnectivityManager)
    fun getAllFruits()
    fun destroy()
}

class AllFruitsPresenterImpl(
    private val fruitsDAO: FruitsDAO,
    private var viewContractAllFruits: ViewContractAllFruits? = null,
    private val disposables: CompositeDisposable = CompositeDisposable(),
    private val repository: FruitsRepository = FruitsRepositoryImpl(fruitsDAO)
) : AllFruitsPresenter {

//    @Inject
//    lateinit var contractAllFruits: ViewContractAllFruits

    override fun init(viewContract: ViewContractAllFruits) {
        viewContractAllFruits = viewContract
    }

    override fun checkNetworkConnection(connectivityManager: ConnectivityManager) {
        connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)?.let {
            if (!it.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)) {
                viewContractAllFruits?.onFailure(Throwable("No internet connection"))
            }
        }
    }

    override fun getAllFruits() {
        viewContractAllFruits?.loadingFruits(true)

        repository.getAllFruits()
            .subscribe(
                { fruits -> viewContractAllFruits?.onSuccess(fruits) },
                { error -> viewContractAllFruits?.onFailure(error) }
            )
            .also { disposables.add(it) }
    }

    override fun destroy() {
        disposables.clear()
        viewContractAllFruits = null
    }

}

interface ViewContractAllFruits {
    fun loadingFruits(isLoading: Boolean)
    fun onSuccess(fruits: List<FruitDomain>)
    fun onFailure(error: Throwable)
}

