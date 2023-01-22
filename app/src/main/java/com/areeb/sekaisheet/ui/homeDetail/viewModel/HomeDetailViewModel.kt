package com.areeb.sekaisheet.ui.homeDetail.viewModel

import android.app.Activity
import android.app.WallpaperManager
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.areeb.sekaisheet.data.Resource
import com.areeb.sekaisheet.data.models.unsplashModels.WallpaperUnSplashDtoItem
import com.areeb.sekaisheet.data.repository.homeDetailRepo.HomeDetailRepository
import com.areeb.sekaisheet.ui.base.viewModel.BaseViewModel
import com.areeb.sekaisheet.utils.Constants.ActivityToFragment.Companion.WALLPAPER_ID
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.MalformedURLException
import java.net.URL
import javax.inject.Inject

@HiltViewModel
class HomeDetailViewModel @Inject constructor(
    private val repository: HomeDetailRepository
) : BaseViewModel() {

    companion object {
        private const val TAG = "homeDetailViewModel"
        private const val SET_WALLPAPER_REQUEST_CODE = 1112
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
        isHomeScreen: Boolean,
    ) {

        val permission =
            ContextCompat.checkSelfPermission(context, android.Manifest.permission.SET_WALLPAPER)
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                context as Activity,
                arrayOf(android.Manifest.permission.SET_WALLPAPER),
                SET_WALLPAPER_REQUEST_CODE
            )
        } else {
            showProgressDialog(fragmentManager)
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    try {
                        if (_wallpaperToSet.value != null) {
                            val inputStream =
                                withContext(Dispatchers.IO) { URL(_wallpaperToSet.value).openStream() }
                            val bitmap = BitmapFactory.decodeStream(inputStream)
                            val wallpaperManager = WallpaperManager.getInstance(context)

                            if (isHomeScreen) {
                                wallpaperManager.setBitmap(
                                    bitmap,
                                    null,
                                    true,
                                    WallpaperManager.FLAG_SYSTEM
                                )
                            } else {
                                wallpaperManager.setBitmap(
                                    bitmap,
                                    null,
                                    true,
                                    WallpaperManager.FLAG_LOCK
                                )
                            }
                        } else {
                            Toast.makeText(
                                context,
                                "Error setting wallpaper: Invalid URL",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } catch (e: MalformedURLException) {
                        Toast.makeText(
                            context,
                            "Error setting wallpaper: Invalid URL",
                            Toast.LENGTH_SHORT
                        ).show()

                    }
                }
            }
        }
    }
}
