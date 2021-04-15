package com.example.architecturesample.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.architecturesample.R
import com.example.architecturesample.databinding.MainActivityBinding
import com.gondev.imagelist.util.dataBinding

class MainActivity : AppCompatActivity() {

    private val binding: MainActivityBinding by dataBinding()
    private val navController: NavController by lazy {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_container) as NavHostFragment
        navHostFragment.navController
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(binding.toolbar)
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.fragment_image_list, R.id.fragment_board, R.id.fragment_board))

        navController.graph = navController.navInflater.inflate(R.navigation.nav_main)
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)
        binding.mainBottomNavigation.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when (destination.id) {
                R.id.fragment_image_list, R.id.fragment_board, R.id.fragment_profile -> {
                    binding.mainBottomNavigation.visibility = View.VISIBLE
                }
                else -> {
                    binding.mainBottomNavigation.visibility = View.GONE
                }
            }
        }
    }
}