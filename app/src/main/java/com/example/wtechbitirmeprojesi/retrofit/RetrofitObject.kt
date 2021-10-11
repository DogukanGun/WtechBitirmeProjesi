package com.example.wtechbitirmeprojesi.retrofit

import com.example.wtechbitirmeprojesi.retrofit.dao.ProductsDAO

class RetrofitObject {

    companion object{
        private const val BASE_URL="http://wtech.canerture.com/bilgisayarlar/"


        fun productDAO():ProductsDAO{
            return RetrofitClient.getClient(BASE_URL).create(ProductsDAO::class.java)
        }

    }
}