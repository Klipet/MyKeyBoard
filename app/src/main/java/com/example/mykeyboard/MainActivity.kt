package com.example.mykeyboard

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import com.example.mykeyboard.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupNavigation()
    }


    private fun setupNavigation() {
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        // Set up bottom navigation
        binding.keyboardsTab.setOnClickListener {
            Log.d("MainActivity", "Keyboards tab clicked")
            updateNavigationUI(true)
            navController.navigate(R.id.keyboardFragment)
        }

        binding.fontsTab.setOnClickListener {
            Log.d("MainActivity", "Fonts tab clicked")
            updateNavigationUI(false)
            navController.navigate(R.id.fontsFragment)
        }

        // Listen to navigation changes
        navController.addOnDestinationChangedListener { _: NavController, destination: NavDestination, _: Bundle? ->
            Log.d("MainActivity", "Navigation changed to: ${destination.id}")
            when (destination.id) {
                R.id.keyboardFragment -> {
                    Log.d("MainActivity", "Setting Keyboards as selected")
                    updateNavigationUI(true)
                }
                R.id.fontsFragment -> {
                    Log.d("MainActivity", "Setting Fonts as selected")
                    updateNavigationUI(false)
                }
            }
        }

        // Set initial state
        updateNavigationUI(true)
    }

    private fun updateNavigationUI(isKeyboardsSelected: Boolean) {
        Log.d("MainActivity", "updateNavigationUI called with isKeyboardsSelected: $isKeyboardsSelected")
        if (isKeyboardsSelected) {
            Log.d("MainActivity", "Setting Keyboards tab as selected")
            binding.keyboardsTab.setBackgroundResource(R.drawable.nav_selected)
            binding.fontsTab.setBackgroundResource(R.drawable.nav_butt_not_select)
            binding.keyboardsIndicator.visibility = View.VISIBLE
            binding.fontsIndicator.visibility = View.GONE
        } else {
            Log.d("MainActivity", "Setting Fonts tab as selected")
            binding.keyboardsTab.setBackgroundResource(R.drawable.nav_butt_not_select)
            binding.fontsTab.setBackgroundResource(R.drawable.nav_selected)
            binding.keyboardsIndicator.visibility = View.GONE
            binding.fontsIndicator.visibility = View.VISIBLE
        }
    }
}