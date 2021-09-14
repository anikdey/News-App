package com.anik.newsapp

import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.anik.newsapp.databinding.ActivityHomeBinding
import com.anik.newsapp.ui.NewsViewModel
import com.app.core.base.activity.AppActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppActivity<NewsViewModel, ActivityHomeBinding>() {

    private val viewModel: NewsViewModel by viewModels()

    override fun setViewModel(binding: ActivityHomeBinding) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //binding.bottomNavigation.setupWithNavController(binding.navHostFragment.findNavController())
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment?
        NavigationUI.setupWithNavController(binding.bottomNavigation, navHostFragment!!.navController)

    }

    override val layoutResourceId: Int
        get() = R.layout.activity_home
}