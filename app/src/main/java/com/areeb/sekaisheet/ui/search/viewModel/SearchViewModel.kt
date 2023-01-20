package com.areeb.sekaisheet.ui.search.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.areeb.sekaisheet.data.repository.searchRepository.SearchRepository
import com.areeb.sekaisheet.ui.base.viewModel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: SearchRepository
) : BaseViewModel() {

    private val _searchQuery = MutableLiveData<String>()

    val getSearchQueryList = _searchQuery.switchMap {
        repository.getSearchedWallpaper(it).cachedIn(viewModelScope)
    }

    fun setWallpaper(query: String) {
        _searchQuery.value = query
    }
}