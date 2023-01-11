package com.areeb.sekaisheet.ui.base.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.areeb.sekaisheet.data.Resource

open class BaseViewModel : ViewModel() {
    companion object {
        private const val TAG = " baseViewModel"
    }

    private val _resourceStatus = MutableLiveData<Resource<Any>?>()
    val resourceStatus: LiveData<Resource<Any>?>
        get() = _resourceStatus

    fun setResource(resourceStatus: Resource<Any>?) {
        _resourceStatus.value = resourceStatus
    }

    fun clearResource() {
        _resourceStatus.value = null
    }

    fun setResourceError(error: String) {
        _resourceStatus.value = Resource.Error(
            false,
            null,
            null,
            error
        )
    }
}
