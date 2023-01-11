package com.areeb.sekaisheet.ui.homeDetail.vewModel

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.areeb.sekaisheet.data.Resource
import com.areeb.sekaisheet.data.models.unsplashModels.WallpaperUnSplashDtoItem
import com.areeb.sekaisheet.data.repository.homeDetailRepo.HomeDetailRepository
import com.areeb.sekaisheet.ui.base.viewModel.BaseViewModel
import com.areeb.sekaisheet.utils.Constants.ActivityToFragment.Companion.WALLPAPER_ID
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeDetailViewModel @Inject constructor(
    private val repository: HomeDetailRepository
) : BaseViewModel() {

    private val _wallpaperToSet = MutableLiveData<String>()
    val wallpaperToSet: LiveData<String>
        get() = _wallpaperToSet

    fun saveBundlesArguments(bundle: Bundle?) {
        viewModelScope.launch {
            val wallpaperId = bundle?.getString(WALLPAPER_ID)
            wallpaperId?.let { wallpaperById ->
                getWallpaperToSet(wallpaperById)
            }
        }
    }

    private fun getWallpaperToSet(id: String) {
        viewModelScope.launch {
            setResource(Resource.Loading(true))

            repository.getWallpaperById(id)
                .catch { exceptions ->
                    setResourceError(exceptions.toString())
                }
                .collect {
                    setResponse(it)
                }
        }
    }

    private fun setResponse(response: Resource<WallpaperUnSplashDtoItem>) {
        setResource(response)
        if (response is Resource.Success) {
            _wallpaperToSet.value = response.data.urls.raw
        }
    }
}
