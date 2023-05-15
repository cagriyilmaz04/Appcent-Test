package com.example.appcenttest.Model

import com.google.gson.annotations.SerializedName


data class Genre(
    val id: Long,
    val name: String,
    @SerializedName("picture_small")
    val picture_small: String,
    val type: String
)

