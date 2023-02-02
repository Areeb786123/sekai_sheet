package com.areeb.sekaisheet.ui.Category.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.areeb.sekaisheet.ui.Category.viewModel.CollectionViewModel
import com.areeb.sekaisheet.ui.base.fragment.BaseFragment
import com.example.sekaisheet.databinding.FragmentCategoriesBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CategoriesFragment : BaseFragment() {
    private val viewModel: CollectionViewModel by viewModels()
    private var _fragmentBinding: FragmentCategoriesBinding? = null
    private val fragmentBinding get() = _fragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _fragmentBinding = FragmentCategoriesBinding.inflate(layoutInflater, container, false)
        return _fragmentBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observer()
    }

    private fun observer() {
        viewModel.collectionList.observe(viewLifecycleOwner) {
            Log.e("collectionDataX", it.toString())
        }
    }

}
