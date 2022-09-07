package com.example.fruitsappmvpcat23.database

import androidx.room.*
import com.example.fruitsappmvpcat23.model.domain.FruitDomain
import io.reactivex.Completable
import io.reactivex.Single

/**
 * DAO is the interface to talk to the database making queries
 */
@Dao
interface FruitsDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllFruits(fruits: List<FruitDomain>): Completable

    @Query("SELECT * from fruitdomain")
    fun getLocalFruits(): Single<List<FruitDomain>>

    @Query("SELECT * from fruitdomain WHERE fruitName LIKE :name")
    fun getSpecificFruit(name: String): Single<FruitDomain>

    @Delete
    fun deleteFruit(fruitDomain: FruitDomain): Completable
}