package com.areeb.sekaisheet.ui.search.activity

import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import com.areeb.sekaisheet.ui.base.MainActivity
import com.example.sekaisheet.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchActivity : MainActivity(), NavController.OnDestinationChangedListener {
    companion object {
        private const val TAG = "search Activity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHeaderAndFooter(isBottomVisible = false, isFullScreen = true)
        setUpNavigationGraph()
    }

    private fun setUpNavigationGraph() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        val navGraph = navController.navInflater.inflate(R.navigation.nav_search)
        navController.graph = navGraph
        navController.addOnDestinationChangedListener(this)

    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        Log.e(TAG, TAG)
    }
}