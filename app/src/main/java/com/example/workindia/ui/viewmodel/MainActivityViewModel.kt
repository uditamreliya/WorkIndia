package com.example.workindia.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.workindia.data.model.FetchListResponse
import com.example.workindia.data.model.Item
import com.example.workindia.data.network.ApiInterface
import com.example.workindia.data.repositories.MainActivityRepository
import com.example.workindia.utils.Resource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel constructor() :
    ViewModel() {

    private val retrofitService = ApiInterface.getInstance()
    val productList = MutableLiveData<Resource<List<Item>>>()
    val getItemList: LiveData<Resource<List<Item>>>
        get() = productList

    fun getAllProduct() {

        productList.postValue(Resource.loading(null))
        val response = retrofitService.getAllProducts();
        response.enqueue(object : Callback<FetchListResponse> {
            override fun onResponse(
                call: Call<FetchListResponse>,
                response: Response<FetchListResponse>
            ) {
                response.body()?.also {
                    productList.postValue(Resource.success(it.data.items))
                }

            }

            override fun onFailure(call: Call<FetchListResponse>, t: Throwable) {
                productList.postValue(Resource.error(t.localizedMessage, null))
            }
        })
    }

    fun getProductList(): LiveData<Resource<List<Item>>> {
        return productList
    }

}