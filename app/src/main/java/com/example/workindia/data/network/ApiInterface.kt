package com.example.workindia.data.network

import com.example.workindia.data.model.FetchListResponse
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


interface ApiInterface {

    @GET("v3/b6a30bb0-140f-4966-8608-1dc35fa1fadc/")
    fun getAllProducts(): Call<FetchListResponse>

    companion object {

        var retrofitService: ApiInterface? = null

        fun getInstance() : ApiInterface {

            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://run.mocky.io/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(ApiInterface::class.java)
            }
            return retrofitService!!
        }
    }
}