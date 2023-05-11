package com.example.appcenttest.Repository

import com.example.appcenttest.RetrofitApi


class MainRepository constructor(private val retrofitService:RetrofitApi, private val user_id:String?=null){
    suspend fun getAllGenres() = retrofitService.getGenre()
    suspend fun getAllArtists() = retrofitService.getArtist(user_id!!)
}