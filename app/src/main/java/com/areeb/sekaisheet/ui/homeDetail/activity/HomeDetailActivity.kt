package com.areeb.sekaisheet.ui.homeDetail.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.areeb.sekaisheet.ui.base.MainActivity
import com.areeb.sekaisheet.utils.Constants.ActivityToFragment.Companion.WALLPAPER_ID
import com.example.sekaisheet.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeDetailActivity : MainActivity() {
    companion object {
        private const val TAG = "homeDetailActivity"

        fun newIntent(context: Context, wallpaperId: String) {
            val intent = Intent(context, HomeDetailActivity::class.java)
            intent.putExtra(WALLPAPER_ID, wallpaperId)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHeaderAndFooter(isBottomVisible = false, isFullScreen = true)
        setUpNavigationGraph()
    }

    private fun setUpNavigationGraph() {
        val wallpaperId = intent.getStringExtra(WALLPAPER_ID)

        val bundle = Bundle()
        bundle.putString(WALLPAPER_ID, wallpaperId)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment

        val navController = navHostFragment.navController

        val navGraph = navController.navInflater.inflate(R.navigation.home_detail_navigation)

        navController.setGraph(navGraph, bundle)
    }
}
