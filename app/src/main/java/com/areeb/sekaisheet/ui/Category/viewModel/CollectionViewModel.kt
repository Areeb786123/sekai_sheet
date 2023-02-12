package com.areeb.sekaisheet.ui.Category.viewModel

import com.areeb.sekaisheet.ui.base.viewModel.BaseViewModel
import com.areeb.sekaisheet.utils.DataModelConverter
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CollectionViewModel @Inject constructor(private val dataModelConverter: DataModelConverter) :
    BaseViewModel() {
    companion object {
        private const val TAG = "Collection View Model"
    }



}