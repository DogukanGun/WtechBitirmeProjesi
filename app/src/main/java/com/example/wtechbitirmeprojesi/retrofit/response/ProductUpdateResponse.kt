package com.example.wtechbitirmeprojesi.retrofit.response

import com.google.gson.annotations.SerializedName

data class ProductUpdateResponse(
    @SerializedName("message")
    var message:String,
    @SerializedName("success")
    var status:String

) {
}