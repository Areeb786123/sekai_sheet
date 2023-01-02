package com.areeb.sekaisheet.ui.Home.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.areeb.sekaisheet.data.Resource
import com.areeb.sekaisheet.data.models.Wallpaper.WallpaperResponseDto
import com.areeb.sekaisheet.data.repository.home.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository
) : ViewModel() {
    companion object {
        private const val TAG = "homeViewModel"
    }

    private val _wallpaper = MutableLiveData<WallpaperResponseDto>()
    val wallpaper: LiveData<WallpaperResponseDto>
        get() = _wallpaper

    init {
        getWallpaper()
    }

    private fun getWallpaper() {
        viewModelScope.launch {
            repository.getAllWallpapers()
                .catch { exception ->
                    Log.e(TAG, exception.toString())
                }.collect {
                    setWallpaperResponse(it)
                }
        }
    }

    private fun setWallpaperResponse(resource: Resource<WallpaperResponseDto>) {
        if (resource is Resource.Success) {
            resource.data.let {
                _wallpaper.value = it
            }
        }
    }
}
