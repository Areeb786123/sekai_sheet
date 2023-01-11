package com.areeb.sekaisheet.ui.base

import android.os.Bundle
import android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.areeb.sekaisheet.utils.visible
import com.example.sekaisheet.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
open class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val navHostFragment =
            supportFragmentManager.findFragmentById(binding.fragmentContainerView.id) as NavHostFragment?
        val navController = navHostFragment!!.navController
        binding.bottomNav.setupWithNavController(navController)
    }

    fun setHeaderAndFooter(
        isBottomVisible: Boolean,
        isFullScreen: Boolean
    ) {
        binding.bottomNav.visible(isBottomVisible)

        if (isFullScreen) {
            window.setFlags(
                FLAG_FULLSCREEN,
                FLAG_FULLSCREEN
            )
        }
    }
}
