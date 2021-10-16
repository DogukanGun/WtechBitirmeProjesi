package com.example.wtechbitirmeprojesi.resources

import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.NavHostFragment
import com.example.wtechbitirmeprojesi.room.Database

object Constants {
    lateinit var navHostFragment:NavHostFragment
    lateinit var db:Database
    var noInternetConnection=MutableLiveData(false)

     fun toggleStatus(status:Int):Int{
        if (status==1){
            return 0
        }
        return 1
    }
}