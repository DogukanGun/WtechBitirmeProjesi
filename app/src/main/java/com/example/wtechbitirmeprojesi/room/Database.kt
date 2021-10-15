package com.example.wtechbitirmeprojesi.room

import androidx.room.RoomDatabase
import com.example.wtechbitirmeprojesi.model.Product

@androidx.room.Database(entities = [Product::class], version = 1)
abstract class Database: RoomDatabase() {
    abstract fun productDao(): ProductsDAO
}
