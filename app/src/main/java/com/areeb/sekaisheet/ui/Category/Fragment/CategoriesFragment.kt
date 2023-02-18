package com.areeb.sekaisheet.ui.Category.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.areeb.sekaisheet.data.models.collectionsModel.CollectionsDto
import com.areeb.sekaisheet.ui.Category.adapter.CollectionAdapter
import com.areeb.sekaisheet.ui.Category.viewModel.CollectionViewModel
import com.areeb.sekaisheet.ui.base.fragment.BaseFragment
import com.areeb.sekaisheet.ui.common.itemClick.ItemClickListener
import com.example.sekaisheet.databinding.FragmentCategoriesBinding
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoriesFragment : BaseFragment() {
    private val viewModel: CollectionViewModel by viewModels()
    private var _fragmentBinding: FragmentCategoriesBinding? = null
    private val fragmentBinding get() = _fragmentBinding!!
    private var collectionAdapter: CollectionAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _fragmentBinding = FragmentCategoriesBinding.inflate(layoutInflater, container, false)
        return _fragmentBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        extractCollectionData()
    }

    // Move this function later in viewModel and observe with Live data
    private fun extractCollectionData() {
        val jsonString = context?.assets?.open("collection.json")?.bufferedReader().use {
            it?.readText()
        }
        val collectionsWrapper = Gson().fromJson(jsonString, CollectionsDto::class.java)
        val collections = collectionsWrapper.collections
        Log.e("dataCollection", collections.toString())
        collectionAdapter = CollectionAdapter(
            collections,
            ItemClickListener { collectionItem ->
                onItemClick(collectionItem.title.toString())
            },
        )

        fragmentBinding.collectionRecyclerView.adapter = collectionAdapter
    }

    private fun onItemClick(title: String) {
        Toast.makeText(requireContext(), title, Toast.LENGTH_SHORT).show()
    }
}
