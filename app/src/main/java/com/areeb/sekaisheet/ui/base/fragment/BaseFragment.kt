package com.areeb.sekaisheet.ui.base.fragment

import android.content.Intent
import androidx.fragment.app.Fragment
import com.areeb.sekaisheet.ui.search.activity.SearchActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
open class BaseFragment : Fragment() {

    fun onSearchClick() {
        startActivity(Intent(requireActivity(), SearchActivity::class.java))
    }
}
