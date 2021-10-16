package com.example.wtechbitirmeprojesi.retrofit.dao

import com.example.wtechbitirmeprojesi.model.Product
import com.example.wtechbitirmeprojesi.retrofit.response.ProductResponse
import com.example.wtechbitirmeprojesi.retrofit.response.ProductUpdateResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ProductsDAO {

    @POST("sepet_durum_degistir.php")
    @FormUrlEncoded
    suspend fun updateCard(@Field("id")id:Int,@Field("sepet_durum")cardStatus:Int):Response<ProductUpdateResponse>
    @POST("bilgisayarlar.php")
    @FormUrlEncoded
    suspend fun getProduct(@Field("kullanici")user:String):Response<ProductResponse>
    @POST("bilgisayar_indirimli.php")
    @FormUrlEncoded
    suspend fun getDiscountedProduct(@Field("kullanici")user:String):Response<List<Product>>
    @POST("bilgisayarlar_sepet.php")
    @FormUrlEncoded
    suspend fun getCard(@Field("kullanici")user:String):Response<ProductResponse>
}