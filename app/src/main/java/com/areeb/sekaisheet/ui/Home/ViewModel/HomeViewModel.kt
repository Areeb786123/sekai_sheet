package com.areeb.sekaisheet.ui.Home.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.areeb.sekaisheet.data.repository.home.HomeRepository
import com.areeb.sekaisheet.utils.CollectionData.collectionTitle
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    repository: HomeRepository,
) : ViewModel() {

    val unSplashWallpaperList =
        repository.getAllWallpapers(collectionTitle).cachedIn(viewModelScope)
}
