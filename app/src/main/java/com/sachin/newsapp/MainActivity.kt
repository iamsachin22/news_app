package com.sachin.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHost
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.sachin.newsapp.databinding.ActivityMainBinding
import com.sachin.newsapp.presentation.viewmodel.NewsViewModel
import com.sachin.newsapp.presentation.viewmodel.NewsViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var factory: NewsViewModelFactory
    lateinit var viewModel: NewsViewModel
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment = supportFragmentManager.findFragmentById(
          R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavView.setupWithNavController(navController)
        viewModel = ViewModelProvider(this,factory)
            .get(NewsViewModel::class.java)
    }
}

//@AndroidEntryPoint
//class MainActivity : AppCompatActivity() {
//    @Inject
//    lateinit var factory: NewsViewModelFactory
//    lateinit var viewModel:NewsViewModel
//    private lateinit var binding:ActivityMainBinding
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding= ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//       val navHostFragment = supportFragmentManager.findFragmentById(
//           R.id.fragmentContainerView) as NavHostFragment
//       val navController = navHostFragment.navController
//        binding.bottomNavView.setupWithNavController(navController)
//
//        viewModel = ViewModelProvider(this,factory)[NewsViewModel::class.java]
//
//    }
//}