package com.example.wtechbitirmeprojesi.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Product(
    @PrimaryKey
    @SerializedName("id")
    var id:Int,
     @SerializedName("urun_adi")
    var name:String,
     @SerializedName("kullanici")
    var user:String,
     @SerializedName("gorsel1")
    var imagePath1:String,
    @ColumnInfo(name = "gorsel2")
    @SerializedName("gorsel2")
    var imagePath2:String,
    @ColumnInfo(name = "gorsel3")
    @SerializedName("gorsel3")
    var imagePath3:String,
    @ColumnInfo(name = "fiyat")
    @SerializedName("fiyat")
    var price:String,
    @ColumnInfo(name = "ssd_kapasite")
    @SerializedName("ssd_kapasite")
    var ssdCapacity:String,
    @ColumnInfo(name = "hdd_kapasite")
    @SerializedName("hdd_kapasite")
    var hddCapacity:String,
    @ColumnInfo(name = "ram_kapasite")
    @SerializedName("ram_kapasite")
    var ramCapacity:String,
    @ColumnInfo(name = "dahili_hafiza_tipi")
    @SerializedName("dahili_hafiza_tipi")
    var memoryType:String,
    @ColumnInfo(name = "ekran_boyutu")
    @SerializedName("ekran_boyutu")
    var screenSize:String,
    @ColumnInfo(name = "ekran_cozunurluk")
    @SerializedName("ekran_cozunurluk")
    var screenResolution:String,
    @ColumnInfo(name = "islemci")
    @SerializedName("islemci")
    var cpu:String,
    @ColumnInfo(name = "indirim_durumu")
    @SerializedName("indirim_durumu")
    var isDiscountMade:Int,
    @ColumnInfo(name = "sepet_durumu")
    @SerializedName("sepet_durumu")
    var cardStatus:Int

    )
