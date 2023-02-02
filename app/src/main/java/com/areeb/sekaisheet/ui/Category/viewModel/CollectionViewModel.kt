package com.areeb.sekaisheet.ui.Category.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.areeb.sekaisheet.data.models.collectionsModel.Collection
import com.areeb.sekaisheet.ui.base.viewModel.BaseViewModel
import com.areeb.sekaisheet.utils.Constants
import com.areeb.sekaisheet.utils.DataModelConverter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CollectionViewModel @Inject constructor(private val dataModelConverter: DataModelConverter) :
    BaseViewModel() {
    companion object {
        private const val TAG = "Collection View Model"
    }

    private val _collectionList =
        MutableLiveData<List<com.areeb.sekaisheet.data.models.collectionsModel.Collection>>()
    val collectionList: LiveData<List<com.areeb.sekaisheet.data.models.collectionsModel.Collection>>
        get() = _collectionList

    init {
        getCollectionList()
    }

//    private fun extractCollectionsData(
//        collectionDto: List<com.areeb.sekaisheet.data.models.collectionsModel.Collection>?,
//    ) {
//        collectionDto?.let {
//
//            _collectionList.value = it
//        } ?: kotlin.run {
//            collectionFileName?.let { getCollectionList() }
//        }
//    }

    private fun getCollectionList() {
        viewModelScope.launch {
            getCollectionListFlow()
                .catch {
                    Log.e(TAG, it.toString())
                }
                .collect {
                    _collectionList.value = it
                }
        }

    }

    private fun getCollectionListFlow(): Flow<List<Collection>> {

        return flow {
            emit(dataModelConverter.convertCollectionList(Constants.Collection.COLLECTION_FILE))
        }.flowOn(
            Dispatchers.IO
        )
    }

}