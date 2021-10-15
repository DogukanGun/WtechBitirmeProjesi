package com.example.wtechbitirmeprojesi.retrofit.response

import com.example.wtechbitirmeprojesi.model.Product
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ProductResponse(@SerializedName("bilgisayarlar")
                           var products: List<Product>
):Serializable