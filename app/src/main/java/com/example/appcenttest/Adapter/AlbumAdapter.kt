package com.example.appcenttest.Adapter

import AlbumDetail
import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appcenttest.databinding.RecyclerAlbumBinding
import com.squareup.picasso.Picasso
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.util.Log
import android.widget.Toast
import com.example.appcenttest.R
import com.example.appcenttest.ViewModel.TracksViewModel

class AlbumAdapter (val list: AlbumDetail,val viewModel:TracksViewModel,val context:Context): RecyclerView.Adapter<AlbumAdapter.AlbumDetailVH>() {
    private var mediaPlayer: MediaPlayer? = null
    class AlbumDetailVH(val binding: RecyclerAlbumBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumDetailVH {
        val binding = RecyclerAlbumBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return AlbumDetailVH(binding)
    }

    override fun getItemCount(): Int {
        return list.tracks.data.size
    }

    override fun onBindViewHolder(holder: AlbumDetailVH, position: Int) {
        holder.binding.apply {
            textViewSongName.text = list.tracks.data.get(position).title
            textViewDuration.text = convertSecondsToMinutesAndSeconds(list.tracks.data.get(position).duration!!.toInt())
            Picasso.get().load(list.tracks.data.get(position).album!!.cover_big).into(imageSong)
            imageViewLike.setOnClickListener {
                Toast.makeText(context,context.getString(R.string.save),Toast.LENGTH_LONG).show()
                imageViewLike.setImageResource(R.drawable.baseline_favorite_24)
                viewModel.addTrack(list.tracks.data.get(position))
            }
            cardViewAlbum.setOnClickListener {
                playPreview(list.tracks.data.get(position).preview!!)
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