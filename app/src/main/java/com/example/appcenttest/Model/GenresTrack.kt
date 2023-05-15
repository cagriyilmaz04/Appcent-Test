package com.example.appcenttest.Model

import com.google.gson.annotations.SerializedName

data class GenresTrack(
    val id: Long,
    val name: String,
    val picture: String,
    val type: String
)