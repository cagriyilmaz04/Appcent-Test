package com.example.appcenttest.Model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Artist(
    val id:Int,
    val name:String,
    val picture:String,
    val picture_small:String,
    val picture_medium:String,
    val picture_big:String,
    val picture_xl:String,
    val radio:Boolean,
    val tracklist:String,
    val type:String
) : Parcelable