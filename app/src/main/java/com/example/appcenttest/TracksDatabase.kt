package com.example.appcenttest

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.appcenttest.Model.Tracks
import com.example.appcenttest.Service.TrackDao

@Database(entities = [Tracks::class], version = 1, exportSchema = false)
@TypeConverters(ArtistTypeConverter::class,AlbumTypeConverter::class)
abstract class TracksDatabase: RoomDatabase() {
    abstract fun tracksDao(): TrackDao

    companion object{
        @Volatile
        private var INSTANCE: TracksDatabase?=null

        fun getDatabase(context:Context):TracksDatabase{
            val tempInstance = INSTANCE
            if(tempInstance!=null){
                return tempInstance

            }
            synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext,TracksDatabase::class.java,"tracks_table").build()
                INSTANCE = instance
                return instance
            }
        }
    }

}