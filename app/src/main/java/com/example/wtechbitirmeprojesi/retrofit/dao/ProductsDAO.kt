package com.example.wtechbitirmeprojesi.retrofit.dao

import com.example.wtechbitirmeprojesi.model.Product
import com.example.wtechbitirmeprojesi.retrofit.response.ProductResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ProductsDAO {

    @POST("sepet_durum_degistir.php")
    suspend fun updateCard(id:Int,@Field("sepet_durumu")cardStatus:Int)
    @POST("bilgisayarlar.php")
    @FormUrlEncoded
    suspend fun getProduct(@Field("kullanici")user:String):Response<ProductResponse>
    @POST("bilgisayar_indirimli.php")
    @FormUrlEncoded
    suspend fun getDiscountedProduct(@Field("kullanici")user:String):Response<List<Product>>
    @POST("bilgisayar_sepet.php")
    @FormUrlEncoded
    suspend fun getCard(@Field("kullanici")user:String):Response<List<Product>>
}