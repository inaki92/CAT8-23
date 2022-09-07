package com.example.fruitsappmvpcat23.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import com.example.fruitsappmvpcat23.model.domain.FruitDomain

/**
 * This is the database object to get access to the DAO
 *
 * You need to pass the table you are working on and the VERSION
 *
 * Everytime you add a new field to the Entity you need to increase the version of the database
 *
 * STEPS:
 * 1. Annotate your data class with entity to be used as a table
 * 2. Create the DAO interface to write all your queries
 * 3. Create the ABSTRACT class for the database in order to expose the DAO interface
 * 4. Extend the [RoomDatabase] class
 * 5. Build the Room DB from the context
 */
@Database(entities = [FruitDomain::class], version = 1)
abstract class FruitsDatabase : RoomDatabase() {
    abstract fun getFruitsDao(): FruitsDAO
}

/**
 * Adding a migration everytime you add a new property or column to the Entity [FruitDomain]
 */
val MIGRATION_1_2 = Migration(1, 2) { database ->
    database.execSQL("ALTER TABLE fruitDomain ADD COLUMN id INTEGER")
}