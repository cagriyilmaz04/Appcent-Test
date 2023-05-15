package com.example.appcenttest

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.appcenttest.Model.Tracks
@Dao
interface TrackDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addTrack(track:Tracks)

    @Query("SELECT * FROM tracks_table ORDER BY room_id ASC")
    fun readAllData(): LiveData<List<Tracks>>

    @Delete
    fun deleteTrack(track: Tracks)
}