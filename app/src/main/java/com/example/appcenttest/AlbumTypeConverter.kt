package com.example.appcenttest

import androidx.room.TypeConverter
import com.example.appcenttest.Model.Album

import com.google.gson.Gson


class AlbumTypeConverter {
    @TypeConverter
    fun fromAlbum(album: Album?): String {
        val gson = Gson()
        return gson.toJson(album)
    }

    @TypeConverter
    fun toAlbum(albumJson: String?): Album {
        val gson = Gson()
        return gson.fromJson(albumJson, Album::class.java)
    }
}
