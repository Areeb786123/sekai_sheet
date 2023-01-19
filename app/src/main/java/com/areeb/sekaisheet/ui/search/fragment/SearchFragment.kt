package com.areeb.sekaisheet.ui.search.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import com.areeb.sekaisheet.ui.base.fragment.BaseFragment
import com.example.sekaisheet.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment() {

    private var _fragmentBinding: FragmentSearchBinding? = null
    private val fragmentBinding get() = _fragmentBinding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _fragmentBinding = FragmentSearchBinding.inflate(
            layoutInflater,
            container,
            false
        )
        setUpSearchView()

        return _fragmentBinding?.root
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
                InputMethodManager.HIDE_NOT_ALWAYS
            )

        }

    }
}