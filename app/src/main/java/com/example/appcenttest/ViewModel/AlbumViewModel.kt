package com.example.appcenttest.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appcenttest.Model.AlbumResponse
import com.example.appcenttest.Repository.MainRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AlbumViewModel (private val mainRepository: MainRepository): ViewModel() {
    val errorMessage = MutableLiveData<String>()
    val albumDetail = MutableLiveData<AlbumResponse>()
    var job: Job? = null

    val loading = MutableLiveData<Boolean>()

    fun getAllAlbums() {
        job = CoroutineScope(Dispatchers.IO).launch {
            val response = mainRepository.getAlbumDetails()
            withContext(Dispatchers.Main) {
                albumDetail.postValue(response.body())
                loading.value = false

            }

        }

    }
    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}