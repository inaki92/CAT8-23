package com.example.fruitsappmvpcat23.rest

import android.util.Log
import com.example.fruitsappmvpcat23.database.FruitsDAO
import com.example.fruitsappmvpcat23.database.LocalFruitsRepository
import com.example.fruitsappmvpcat23.database.LocalFruitsRepositoryImpl
import com.example.fruitsappmvpcat23.model.domain.FruitDomain
import com.example.fruitsappmvpcat23.model.domain.mapToDomainFruit
import com.example.fruitsappmvpcat23.model.domain.mapToDomainFruitList
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

private const val TAG = "FruitsRepository"

interface FruitsRepository {
    fun getAllFruits(): Single<List<FruitDomain>>
    fun searchFruit(fruitName: String): Single<FruitDomain>
}

class FruitsRepositoryImpl @Inject constructor(
    private val fruitsApi: FruitsApi,
    private val ioScheduler: Scheduler,
    private val localFruitsRepository: LocalFruitsRepository
) : FruitsRepository {

    override fun getAllFruits(): Single<List<FruitDomain>> =
        fruitsApi.getAllFruits()
            .flatMap {
                localFruitsRepository.insertFruits(it.mapToDomainFruitList()).subscribe()
                localFruitsRepository.getAllFruits()
            }
            .subscribeOn(ioScheduler)
            .observeOn(AndroidSchedulers.mainThread())

    override fun searchFruit(fruitName: String): Single<FruitDomain> =
        fruitsApi.searchFruit(fruitName)
            .map { it.mapToDomainFruit() }
            .subscribeOn(ioScheduler)
            .observeOn(AndroidSchedulers.mainThread())

}