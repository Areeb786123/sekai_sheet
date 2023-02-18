package com.areeb.sekaisheet.ui.Home.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.areeb.sekaisheet.data.Resource
import com.areeb.sekaisheet.data.models.unsplashModels.WallpaperUnSplashDtoItem
import com.areeb.sekaisheet.data.repository.home.HomeRepository
import com.areeb.sekaisheet.ui.base.viewModel.BaseViewModel
import com.areeb.sekaisheet.utils.CollectionData.collectionTitle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository,
) : BaseViewModel() {

    private val _unsSplashWallpaperList = MutableLiveData<PagingData<WallpaperUnSplashDtoItem>>()
    val unSplashWallpaperList: LiveData<PagingData<WallpaperUnSplashDtoItem>>
        get() = _unsSplashWallpaperList

    init {
        getWallpaperList()
    }

    private fun getWallpaperList() {
        viewModelScope.launch {
            setResource(Resource.Loading(true))
            repository.getAllWallpapers(collectionTitle)
                .catch {
                    Resource.Error(
                        false,
                        null,
                        null,
                        it.message.toString(),
                    )
                }
                .collect {
                    setResource(Resource.Success(true))
                    _unsSplashWallpaperList.value = it
                }
        }
    }
}
