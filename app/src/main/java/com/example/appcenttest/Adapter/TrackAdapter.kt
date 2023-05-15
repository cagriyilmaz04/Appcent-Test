package com.example.appcenttest.Adapter

import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.appcenttest.Model.Tracks
import com.example.appcenttest.R
import com.example.appcenttest.View.homeFragment
import com.example.appcenttest.ViewModel.TracksViewModel
import com.example.appcenttest.databinding.RecyclerAlbumBinding
import com.example.appcenttest.databinding.RecyclerFavoriteBinding
import com.squareup.picasso.Picasso

class TrackAdapter (val list: ArrayList<Tracks>,val listener:TrackListener): RecyclerView.Adapter<TrackAdapter.TrackVH>() {
    private var mediaPlayer: MediaPlayer? = null
    interface TrackListener{
        fun onItemClick(value:Int)
    }
    class TrackVH(val binding: RecyclerFavoriteBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackVH {
        val binding = RecyclerFavoriteBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TrackVH(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: TrackVH, position: Int) {
        homeFragment.albumName = list.get(0).title.toString()
        holder.binding.apply {
            textViewSongNameTrack.text = list.get(position).title
            textViewDurationTracks.text = convertSecondsToMinutesAndSeconds(list.get(position).duration!!.toInt())
            Picasso.get().load(list.get(position).album!!.cover_big).into(imageSongTrack)
            imageViewLikeTracks.setOnClickListener {
                imageViewLikeTracks.setImageResource(R.drawable.baseline_favorite_border_24)
                //list.removeAt(position)
                listener.onItemClick(position)

            }
            cardViewAlbum.setOnClickListener {
                playPreview(list.get(position).preview!!)
            }
        }
    }
    fun convertSecondsToMinutesAndSeconds(seconds: Int): String {
        val minutes = seconds / 60
        val remainingSeconds = seconds % 60
        return "$minutes:$remainingSeconds"
    }
    private fun playPreview(url: String) {
        mediaPlayer?.let {
            if (it.isPlaying) {
                it.stop()
                it.reset()
                it.release()
            }
        }
        mediaPlayer = MediaPlayer().apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()
            )
            setDataSource(url)
            prepareAsync()
            setOnPreparedListener {
                start()
            }
        }
    }

}