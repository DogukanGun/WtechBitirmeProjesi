package com.example.wtechbitirmeprojesi.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wtechbitirmeprojesi.model.Product
import com.example.wtechbitirmeprojesi.retrofit.RetrofitObject
import kotlinx.coroutines.*

class CardViewModel:ViewModel() {
    val card = MutableLiveData(emptyList<Product>())
    var loading=MutableLiveData(false)
    var job:Job?=null
    private val errorMessage=MutableLiveData("")
    fun request(){
        loading.value=true
        job = CoroutineScope(Dispatchers.IO+exceptionHandler).launch {
            val response = RetrofitObject.productDAO().getCard("dogukangundogan")
            withContext(Dispatchers.Main){
                if (response.isSuccessful){
                    card.value = response.body()!!
                }else{
                    onError("Error ${response.message()}")
                }
            }
        }

    }

    private fun onError(message:String){
        loading.value=false
        errorMessage.value=message
    }
    var exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        errorMessage.postValue("Api Error"+throwable.localizedMessage)
        Log.d("CardViewModel ",throwable.localizedMessage!!)
    }
}