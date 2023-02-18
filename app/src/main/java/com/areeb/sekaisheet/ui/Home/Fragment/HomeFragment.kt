package com.areeb.sekaisheet.ui.Home.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.areeb.sekaisheet.ui.Home.Adapter.HomeAdapter
import com.areeb.sekaisheet.ui.Home.ViewModel.HomeViewModel
import com.areeb.sekaisheet.ui.base.fragment.BaseFragment
import com.areeb.sekaisheet.ui.common.itemClick.ItemClickListener
import com.areeb.sekaisheet.ui.homeDetail.activity.HomeDetailActivity
import com.example.sekaisheet.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment(), View.OnClickListener {

    private val viewModel: HomeViewModel by viewModels()
    private var _fragmentBinding: FragmentHomeBinding? = null
    private val fragmentBinding get() = _fragmentBinding!!
    private var homeAdapter: HomeAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        _fragmentBinding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        return _fragmentBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        observer()
    }

    private fun init() {
        homeAdapter = HomeAdapter(
            ItemClickListener {
                onWallpaperSelectedItemClick(it.id)
            },
        )
        fragmentBinding.homeRecyclerView.adapter = homeAdapter
    }

    private fun observer() {
        viewModel.unSplashWallpaperList.observe(viewLifecycleOwner) {
            homeAdapter?.submitData(viewLifecycleOwner.lifecycle, it)
        }
    }

    override fun onResume() {
        super.onResume()
        setOnViewClickListener()
    }

    private fun onWallpaperSelectedItemClick(wallpaperId: String) {
        HomeDetailActivity.newIntent(requireContext(), wallpaperId)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            fragmentBinding.searchAnimatedView.id -> {
                onSearchClick()
            }
        }
    }

    private fun setOnViewClickListener() {
        fragmentBinding.searchAnimatedView.setOnClickListener(this)
    }
}
