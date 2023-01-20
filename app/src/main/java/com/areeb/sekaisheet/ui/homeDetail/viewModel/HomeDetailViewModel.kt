package com.areeb.sekaisheet.ui.homeDetail.viewModel

import android.app.WallpaperManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.areeb.sekaisheet.data.Resource
import com.areeb.sekaisheet.data.models.unsplashModels.WallpaperUnSplashDtoItem
import com.areeb.sekaisheet.data.repository.homeDetailRepo.HomeDetailRepository
import com.areeb.sekaisheet.ui.base.viewModel.BaseViewModel
import com.areeb.sekaisheet.utils.Constants.ActivityToFragment.Companion.WALLPAPER_ID
import com.bumptech.glide.Glide
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeDetailViewModel @Inject constructor(
    private val repository: HomeDetailRepository
) : BaseViewModel() {

    companion object {
        private const val TAG = "homeDetailViewModel"
    }

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

    @RequiresApi(Build.VERSION_CODES.N)
    fun setWallpaperToHomeScreen(
        context: Context,
        fragmentManager: FragmentManager,
        isHomeScreen: Boolean
    ) {
        showProgressDialog(fragmentManager)
        viewModelScope.launch {
            val bitmap = withContext(Dispatchers.IO) {
                Glide.with(context)
                    .asBitmap()
                    .load(_wallpaperToSet.value)
                    .submit()
                    .get()

            }

            if (isHomeScreen) {
                WallpaperManager.getInstance(context)
                    .setBitmap(bitmap, null, true, WallpaperManager.FLAG_SYSTEM)
            } else {
                WallpaperManager.getInstance(context)
                    .setBitmap(bitmap, null, true, WallpaperManager.FLAG_LOCK)
            }
            Toast.makeText(context, "Wallpaper Set Successfully ❤️ ", Toast.LENGTH_SHORT).show()


        }
    }

}
