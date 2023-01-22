package com.areeb.sekaisheet.ui.base.fragment

import android.content.Intent
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.areeb.sekaisheet.ui.search.activity.SearchActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
open class BaseFragment : Fragment() {

    companion object {
        private const val TAG = "BaseFragment"
    }

    fun onSearchClick() {
        startActivity(Intent(requireActivity(), SearchActivity::class.java))
    }

    fun safeNavigate(navDirections: Int) {
        try {

            findNavController().navigate(navDirections)

        } catch (exception: IllegalStateException) {
            Log.e(TAG, exception.toString())
        }
    }

}
