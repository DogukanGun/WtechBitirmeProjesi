package com.example.wtechbitirmeprojesi.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.wtechbitirmeprojesi.model.Product

@Dao
interface ProductsDAO {
    @Query("Select * from product where id in (:productId)")
    fun getProductById(productId:Int):Product?

    @Query("Select * from product")
    fun getProducts():List<Product>

    @Insert
    fun addProduct(product:Product)
}