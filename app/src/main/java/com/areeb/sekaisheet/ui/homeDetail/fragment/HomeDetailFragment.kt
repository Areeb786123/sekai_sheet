package com.areeb.sekaisheet.ui.homeDetail.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.areeb.sekaisheet.data.Resource
import com.areeb.sekaisheet.ui.base.fragment.BaseFragment
import com.areeb.sekaisheet.ui.homeDetail.vewModel.HomeDetailViewModel
import com.areeb.sekaisheet.utils.setImageView
import com.example.sekaisheet.databinding.FragmentHomeDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeDetailFragment : BaseFragment() {

    companion object {
        private const val TAG = "DetailFragment"
    }
    private val viewModel: HomeDetailViewModel by viewModels()
    private var _fragmentBinding: FragmentHomeDetailBinding? = null
    private val fragmentBinding get() = _fragmentBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _fragmentBinding = FragmentHomeDetailBinding.inflate(
            layoutInflater,
            container,
            false
        )

        return _fragmentBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.saveBundlesArguments(arguments)
        setObserver()
    }

    private fun setObserver() {
        viewModel.wallpaperToSet.observe(viewLifecycleOwner) { wallpaperUrl ->
            setImageView(fragmentBinding.wallpaperToSetImageView, wallpaperUrl)
        }

        viewModel.resourceStatus.observe(viewLifecycleOwner) {
            setResourceStatus(it)
        }
    }

    private fun setResourceStatus(resource: Resource<Any>?) {
        when (resource) {
            is Resource.Success -> {
                fragmentBinding.progressBar.visibility = View.GONE
                viewModel.clearResource()
            }

            is Resource.Loading -> {
                fragmentBinding.progressBar.visibility = View.VISIBLE
            }

            is Resource.Error -> {
                fragmentBinding.progressBar.visibility = View.VISIBLE
            }
            else -> {
                Log.e("Tag", TAG)
            }
        }
    }
}
