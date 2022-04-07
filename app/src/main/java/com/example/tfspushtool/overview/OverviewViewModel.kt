package com.example.tfspushtool.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tfspushtool.network.MarsApi
import kotlinx.coroutines.launch

class OverviewViewModel  : ViewModel()  {

    init {
        getMarsPhotos()
    }

    private fun getMarsPhotos() {
        viewModelScope.launch {
            val listResult = MarsApi.retrofitService.getPhotos()
        }
    }
}