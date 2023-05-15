package com.example.appcenttest.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tracks_table")
data class Tracks (
    @PrimaryKey(autoGenerate = true)
    val room_id:Int,
    val id: Int?,
    val readable: Boolean?,
    val title: String?,
    val title_short: String?,
    val title_version: String?,
    val link: String?,
    val duration: Int?,
    val rank: Int?,
    val explicit_lyrics: Boolean?,
    val explicit_content_lyrics: Int?,
    val explicit_content_cover: Int?,
    val preview: String?,
    val md5_image: String?,
    val artist: Artist?,
    val album: Album?,
    val type: String?
)