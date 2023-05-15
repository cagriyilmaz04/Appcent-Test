package com.example.appcenttest.Repository

import androidx.lifecycle.LiveData
import com.example.appcenttest.Model.Tracks
import com.example.appcenttest.TrackDao

class TracksRepository(private val tracksDao:TrackDao) {
    val readAllData: LiveData<List<Tracks>> = tracksDao.readAllData()

  fun addTrack(track:Tracks){
        tracksDao.addTrack(track)
    }

  fun deleteTrack(track: Tracks){


  }

}