package com.areeb.sekaisheet.ui.base

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.areeb.sekaisheet.utils.visible
import com.example.sekaisheet.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
open class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    companion object {
        private const val TAG = "Main Activity"
        fun intent(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }

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
        isFullScreen: Boolean,
    ) {
        binding.bottomNav.visible(isBottomVisible)
    }
}
