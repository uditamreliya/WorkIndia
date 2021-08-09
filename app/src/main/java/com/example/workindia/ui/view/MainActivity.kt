package com.example.workindia.ui.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.workindia.R
import com.example.workindia.data.network.ApiInterface
import com.example.workindia.data.repositories.MainActivityRepository
import com.example.workindia.databinding.ActivityMainBinding
import com.example.workindia.ui.viewmodel.MainActivityViewModel
import com.example.workindia.ui.viewmodel.MyViewModelFactory


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var viewModel: MainActivityViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_component) as NavHostFragment
        navController = navHostFragment.navController
        setupNavControl()

        viewModel = ViewModelProvider(
            this,
            MyViewModelFactory()
        ).get(MainActivityViewModel::class.java)

        viewModel.getAllProduct()
    }

    private fun setupNavControl() {
        binding.bottomNavigation.setupWithNavController(navController) //Setup Bottom navigation with navController
    }
}
