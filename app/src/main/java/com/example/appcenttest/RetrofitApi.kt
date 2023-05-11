package com.example.appcenttest

import com.example.appcenttest.Model.ArtistsResponse
import com.example.appcenttest.Model.GenresResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitApi {
    @GET("genre")
    suspend fun getGenre(): Response<GenresResponse>

    @GET("genre/{genre_id}/artists")
    suspend fun getArtist(@Path("genre_id") genre_id:String): Response<ArtistsResponse>


    companion object {
        val baseUrl = "https://api.deezer.com/"
        var retrofitService: RetrofitApi? = null
        fun getInstance(): RetrofitApi{
            if(retrofitService==null){
                val retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitApi::class.java)
            }
            return retrofitService!!
        }

    }
}