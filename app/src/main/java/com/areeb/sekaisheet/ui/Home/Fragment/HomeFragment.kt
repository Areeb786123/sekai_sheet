package com.areeb.sekaisheet.ui.Home.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.areeb.sekaisheet.data.Resource
import com.areeb.sekaisheet.ui.Home.Adapter.HomeAdapter
import com.areeb.sekaisheet.ui.Home.ViewModel.HomeViewModel
import com.areeb.sekaisheet.ui.base.fragment.BaseFragment
import com.areeb.sekaisheet.ui.common.itemClick.ItemClickListener
import com.areeb.sekaisheet.ui.homeDetail.activity.HomeDetailActivity
import com.areeb.sekaisheet.utils.visible
import com.example.sekaisheet.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment(), View.OnClickListener {

    private val viewModel: HomeViewModel by viewModels()
    private var _fragmentBinding: FragmentHomeBinding? = null
    private val fragmentBinding get() = _fragmentBinding!!
    private var homeAdapter: HomeAdapter? = null

    companion object {
        private const val TAG = "homeFragment"
    }

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
        viewModel.resourceStatus.observe(viewLifecycleOwner) {
            it?.let { it1 -> setResourceStatus(it1) }
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

    private fun setResourceStatus(resource: Resource<Any?>) {
        Log.e("hh", resource.toString())
        when (resource) {
            is Resource.Success -> {
                fragmentBinding.homeRecyclerView.visible(true)
                fragmentBinding.homeShimmerLayout.visible(false)
            }
            is Resource.Loading -> {
                fragmentBinding.homeRecyclerView.visible(false)
                fragmentBinding.homeShimmerLayout.visible(true)
            }
            else -> {
                Log.e(TAG, "Some error occur")
            }
        }
    }
}
