package com.example.workindia.data.repositories

import com.example.workindia.data.network.ApiInterface

class MainActivityRepository constructor(private val apiInterface: ApiInterface) {

    fun getAllProducts() = apiInterface.getAllProducts();
}