package com.example.fruitsappmvpcat23.rest

import com.example.fruitsappmvpcat23.model.domain.FruitDomain
import com.example.fruitsappmvpcat23.model.domain.mapToDomainFruit
import com.example.fruitsappmvpcat23.model.domain.mapToDomainFruitList
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

interface FruitsRepository {
    fun getAllFruits(): Single<List<FruitDomain>>
    fun searchFruit(fruitName: String): Single<FruitDomain>
}

class FruitsRepositoryImpl(
    private val fruitsApi: FruitsApi = Service.fruitsApi,
    private val ioScheduler: Scheduler = Schedulers.io()
) : FruitsRepository {

    override fun getAllFruits(): Single<List<FruitDomain>> =
        fruitsApi.getAllFruits()
            .map { it.mapToDomainFruitList() }
            .subscribeOn(ioScheduler)
            .observeOn(AndroidSchedulers.mainThread())

    override fun searchFruit(fruitName: String): Single<FruitDomain> =
        fruitsApi.searchFruit(fruitName)
            .map { it.mapToDomainFruit() }
            .subscribeOn(ioScheduler)
            .observeOn(AndroidSchedulers.mainThread())

}