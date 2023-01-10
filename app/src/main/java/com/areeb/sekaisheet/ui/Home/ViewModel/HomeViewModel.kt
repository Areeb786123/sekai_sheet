package com.areeb.sekaisheet.ui.Home.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.areeb.sekaisheet.data.repository.home.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository
) : ViewModel() {
    companion object {
        private const val TAG = "homeViewModel"
    }

//    private val _wallpaper = MutableLiveData<WallpaperResponseDto>()
//    val wallpaper: LiveData<WallpaperResponseDto>
//        get() = _wallpaper

//    init {
//        getWallpaper()
//    }

//    private fun getWallpaper() {
//        viewModelScope.launch {
//            repository.getAllWallpapers()
//                .catch { exception ->
//                    Log.e(TAG, exception.toString())
//                }.collect {
//                    setWallpaperResponse(it)
//                }
//        }
//    }
//
//    private fun setWallpaperResponse(resource: Resource<WallpaperResponseDto>) {
//        if (resource is Resource.Success) {
//            resource.data.let {
//                _wallpaper.value = it
//            }
//        }
//    }

    val unSplashWallpaperList = repository.getAllWallpapers().cachedIn(viewModelScope)
}
