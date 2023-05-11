package com.example.appcenttest.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.appcenttest.Model.ArtistsResponse
import com.example.appcenttest.Repository.MainRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ArtistViewModel (private val mainRepository: MainRepository): ViewModel() {
    val errorMessage = MutableLiveData<String>()
    val artistList = MutableLiveData<ArtistsResponse>()
    var job: Job? = null

    val loading = MutableLiveData<Boolean>()

    fun getAllArtist() {
        job = CoroutineScope(Dispatchers.IO).launch {
            val response = mainRepository.getAllArtists()
            withContext(Dispatchers.Main) {
                artistList.postValue(response.body())
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