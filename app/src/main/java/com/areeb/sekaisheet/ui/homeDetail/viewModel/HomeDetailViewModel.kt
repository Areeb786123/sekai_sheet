package com.areeb.sekaisheet.ui.homeDetail.viewModel

import android.app.WallpaperManager
import android.content.Context
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings.Global.getString
import android.provider.Settings.Secure.getString
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.areeb.sekaisheet.data.Resource
import com.areeb.sekaisheet.data.models.unsplashModels.WallpaperUnSplashDtoItem
import com.areeb.sekaisheet.data.repository.homeDetailRepo.HomeDetailRepository
import com.areeb.sekaisheet.ui.base.viewModel.BaseViewModel
import com.areeb.sekaisheet.utils.Constants.ActivityToFragment.Companion.WALLPAPER_ID
import com.bumptech.glide.Glide
import com.example.sekaisheet.R
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

    @RequiresApi(Build.VERSION_CODES.P)
    fun setWallpaperToHomeScreen(context: Context) {
        //Areeb Cares for you so I have Handled this method carefully
        viewModelScope.launch {
            try {
                //With Respect to android version greater >= Q
                val bitmap = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    Log.e("WhereIgo","inside nogut")
                    withContext(Dispatchers.IO) {
                        ImageDecoder.decodeBitmap(
                            ImageDecoder.createSource(
                                context.contentResolver,
                                Uri.parse("${_wallpaperToSet.value}")
                            )
                        )
                    }
                } else {
                    //With respect to android version less than Q
                    Log.e("WhereIgo","inside lollipop")
                    withContext(Dispatchers.IO) {
                        Glide.with(context)
                            .asBitmap()
                            .load(_wallpaperToSet.value)
                            .submit()
                            .get()
                    }
                }
                WallpaperManager.getInstance(context).setBitmap(bitmap)
                Toast.makeText(context, "Wallpaper Set Successfully ❤️ ", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Log.e("WallpaperViewModel", "Error setting wallpaper", e)
            }
        }
    }
}
