package com.areeb.sekaisheet.ui.search.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import com.areeb.sekaisheet.databinding.FragmentSearchBinding
import com.areeb.sekaisheet.ui.Home.Adapter.HomeAdapter
import com.areeb.sekaisheet.ui.base.fragment.BaseFragment
import com.areeb.sekaisheet.ui.common.itemClick.ItemClickListener
import com.areeb.sekaisheet.ui.homeDetail.activity.HomeDetailActivity
import com.areeb.sekaisheet.ui.search.viewModel.SearchViewModel
import com.areeb.sekaisheet.utils.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment() {

    private val viewModel: SearchViewModel by viewModels()
    private var _fragmentBinding: FragmentSearchBinding? = null
    private val fragmentBinding get() = _fragmentBinding!!
    private var adapter: HomeAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _fragmentBinding = FragmentSearchBinding.inflate(
            layoutInflater,
            container,
            false,
        )
        setUpSearchView()

        return _fragmentBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObserver()
        init()
        searchQuery()
    }

    private fun init() {
        adapter = HomeAdapter(ItemClickListener { onWallpaperSelectClick(it.id) })
        fragmentBinding.searchRecyclerView.adapter = adapter
    }

    private fun onWallpaperSelectClick(wallpaperId: String) {
        HomeDetailActivity.newIntent(requireContext(), wallpaperId)
    }

    private fun setObserver() {
        viewModel.getSearchQueryList.observe(viewLifecycleOwner) {
            adapter?.submitData(viewLifecycleOwner.lifecycle, it)
            fragmentBinding.searchAnimatedView.visible(false)
            fragmentBinding.searchRecyclerView.visible(true)
        }
    }

    private fun searchQuery() {
        fragmentBinding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    if (isSearchAllowed(it)) {
                        viewModel.setWallpaper(it)
                    } else {
                        // Display toast message indicating adult content is not allowed
                        Toast.makeText(context, "Adult content is not allowed", Toast.LENGTH_SHORT).show()
                    }
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    if (isSearchAllowed(it)) {
                        viewModel.setWallpaper(it)
                    } else {
                        // Display toast message indicating adult content is not allowed
                        Toast.makeText(context, "Adult content is not allowed", Toast.LENGTH_SHORT).show()
                    }
                }
                return true
            }
        })
    }

    private fun isSearchAllowed(query: String): Boolean {
        // Define your criteria to check if the query contains adult content
        val prohibitedWords = listOf("naked", "sex", "porn")
        for (word in prohibitedWords) {
            if (query.contains(word, ignoreCase = true)) {
                return false
            }
        }
        return true
    }

    override fun onDetach() {
        super.onDetach()
        showSoftwareKeyBoard()
    }

    private fun setUpSearchView() {
        fragmentBinding.searchView.let {
            it.isSubmitButtonEnabled = true
            it.onActionViewExpanded()
            it.isFocusable = true
        }
    }

    private fun showSoftwareKeyBoard() {
        val inputManager: InputMethodManager =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        requireActivity().currentFocus?.let {
            inputManager.hideSoftInputFromWindow(
                it.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS,
            )
        }
    }
}
