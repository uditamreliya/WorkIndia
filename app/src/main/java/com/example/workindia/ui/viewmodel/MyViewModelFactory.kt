package com.example.workindia.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.workindia.data.repositories.MainActivityRepository

class MyViewModelFactory constructor() :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
            MainActivityViewModel() as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}