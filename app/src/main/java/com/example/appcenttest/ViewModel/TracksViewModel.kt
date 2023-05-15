package com.example.appcenttest.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.appcenttest.Model.Tracks
import com.example.appcenttest.Repository.TracksRepository
import com.example.appcenttest.TracksDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TracksViewModel(application:Application):AndroidViewModel(application) {
    var readAllData: LiveData<List<Tracks>>
    private val repository: TracksRepository

    init {
        val tracksDao = TracksDatabase.getDatabase(application).tracksDao()
        repository = TracksRepository(tracksDao)
        readAllData = repository.readAllData
    }

    fun addTrack(tracks: Tracks){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addTrack(tracks)
        }
    }

    fun deleteTrack(tracks: Tracks){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteTrack(tracks)
        }
    }




}