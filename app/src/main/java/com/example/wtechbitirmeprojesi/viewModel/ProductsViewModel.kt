package com.example.wtechbitirmeprojesi.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wtechbitirmeprojesi.model.Product
import com.example.wtechbitirmeprojesi.retrofit.RetrofitClient
import com.example.wtechbitirmeprojesi.retrofit.RetrofitObject
import kotlinx.coroutines.*
import okhttp3.Dispatcher

class ProductsViewModel:ViewModel() {

    var job: Job? = null
    var loading = MutableLiveData(false)
    private var errorMessage = MutableLiveData<String>()
    var products:MutableLiveData<List<Product>> = MutableLiveData(emptyList())

    fun request(){
        loading.value=true
        job= CoroutineScope(Dispatchers.IO+exceptionHandler).launch {
            val response = RetrofitObject.productDAO().getProduct("dogukan_ali_gundogan")
            withContext(Dispatchers.Main){
                if (response.isSuccessful){
                    products.value=(listOf(response.body()!!))
                    loading.value=false
                }else{
                    onError("Error : ${response.message()} ")
                }
            }
        }
    }


    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
    }

    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        errorMessage.postValue("ApiError")
        Log.d("ProductViewModel", throwable.localizedMessage!!)

    }
}