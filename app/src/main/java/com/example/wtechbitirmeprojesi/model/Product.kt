package com.example.wtechbitirmeprojesi.model

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("id")
    var id:Int,
    @SerializedName("kullanici")
    var user:String,
    @SerializedName("gorsel1")
    var imagePath1:String,
    @SerializedName("gorsel2")
    var imagePath2:String,
    @SerializedName("gorsel3")
    var imagePath3:String,
    @SerializedName("fiyat")
    var price:Int,
    @SerializedName("ssd_kapasite")
    var ssdCapacity:Int,
    @SerializedName("hdd_kapasite")
    var hddCapacity:Int,
    @SerializedName("ram_kapasite")
    var ramCapacity:Int,
    @SerializedName("dahili_hafiza_tipi")
    var memoryType:String,
    @SerializedName("ekran_boyutu")
    var screenSize:String,
    @SerializedName("ekran_cozunurluk")
    var screenResolution:String,
    @SerializedName("islemci")
    var cpu:String,
    @SerializedName("indirim_durumu")
    var isDiscountMade:Int,
    @SerializedName("sepet_durumu")
    var cardStatus:Int

    )
