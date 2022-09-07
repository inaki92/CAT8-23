package com.example.fruitsappmvpcat23.database

import com.example.fruitsappmvpcat23.model.domain.FruitDomain
import io.reactivex.Completable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * This [LocalFruitsRepository] allows you to have a clean architecture that will increase the testability.
 *
 * This is used to talk to your ROOM DB and retrieve all the information from there
 *
 * STEPS:
 * 1. Create the REPOSITORY INTERFACE
 * 2. Create the REPOSITORY IMPLEMENTATION CLASS
 * 3. Override all the methods
 *
 * NOTE: Everytime you are using the [LocalFruitsRepository] you need to access the interface instead of the implementation
 */
interface LocalFruitsRepository {
    fun insertFruits(fruits: List<FruitDomain>): Completable
    fun getAllFruits(): Single<List<FruitDomain>>
    fun getSpecificFruit(fruitName: String): Single<FruitDomain>
}

class LocalFruitsRepositoryImpl(
    private val fruitsDatabase: FruitsDAO,
    private val ioScheduler: Scheduler = Schedulers.io()
) : LocalFruitsRepository{

    override fun insertFruits(fruits: List<FruitDomain>): Completable =
        fruitsDatabase.insertAllFruits(fruits)
            .subscribeOn(ioScheduler)

    override fun getAllFruits(): Single<List<FruitDomain>> =
        fruitsDatabase.getLocalFruits()
            .subscribeOn(ioScheduler)
            .observeOn(AndroidSchedulers.mainThread())

    override fun getSpecificFruit(fruitName: String): Single<FruitDomain> =
        fruitsDatabase.getSpecificFruit(fruitName)
            .subscribeOn(ioScheduler)
            .observeOn(AndroidSchedulers.mainThread())

}