package com.example.wtechbitirmeprojesi.retrofit.dao

import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.POST

interface ProductsDAO {

    @POST("sepet_durum_degistir.php")
    suspend fun updateCard(id:Int,@Field("sepet_durumu")cardStatus:Int)
    @GET("bilgisayar.php")
    suspend fun getProduct(@Field("kullanici")user:String)
    @GET("bilgisayar_indirimli")
    suspend fun getDiscountedProduct(@Field("kullanici")user:String)
    @GET("bilgisayar_sepet")
    suspend fun getCard(@Field("kullanici")user:String)
}