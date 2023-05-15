package com.example.appcenttest

import androidx.room.TypeConverter
import com.example.appcenttest.Model.Artist
import com.google.gson.Gson

class ArtistTypeConverter {
    @TypeConverter
    fun fromArtist(artist: Artist): String {
        return Gson().toJson(artist)
    }

    @TypeConverter
    fun toArtist(json: String): Artist {
        return Gson().fromJson(json, Artist::class.java)
    }
}
