package com.example.wtechbitirmeprojesi.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wtechbitirmeprojesi.model.Product
import com.example.wtechbitirmeprojesi.resources.Constants
import com.example.wtechbitirmeprojesi.retrofit.RetrofitObject
import kotlinx.coroutines.*

class ProductDetailViewModel:ViewModel() {

    var job: Job? = null
    var loading = MutableLiveData(false)
    private var errorMessage = MutableLiveData<String>()
    var product: MutableLiveData<Product> = MutableLiveData(Product(0,"","","","","","","","","","","","","",0,0))

    fun getProduct(id:Int){
        loading.value=true
        job= CoroutineScope(Dispatchers.IO+exceptionHandler).launch {
            val response = RetrofitObject.productDAO().getProduct("dogukangundogan")
            withContext(Dispatchers.Main){
                if (response.isSuccessful){
                    for(index in (response.body()!!).products){
                        if (index.id==id){
                            loading.value=false
                            product.value=index
                        }
                    }
                }else{
                    onError("Error : ${response.message()} ")
                    Constants.noInternetConnection.value=true
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