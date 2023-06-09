package com.example.appcenttest.Adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.appcenttest.Model.AlbumResponse
import com.example.appcenttest.View.artistDetailFragmentDirections
import com.example.appcenttest.View.homeFragment
import com.example.appcenttest.databinding.RecyclerRowArtistDetailBinding
import com.squareup.picasso.Picasso

class AlbumDetailAdapter (val list: AlbumResponse): RecyclerView.Adapter<AlbumDetailAdapter.AlbumVH>() {
    class AlbumVH(val binding: RecyclerRowArtistDetailBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumVH {
        val binding = RecyclerRowArtistDetailBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return AlbumVH(binding)
    }

    override fun getItemCount(): Int {
        return list.data.size
    }

    override fun onBindViewHolder(holder: AlbumVH, position: Int) {
        holder.binding.apply {
            textAlbumDetailDate.text = list.data[position].release_date
            textAlbumDetail.text = list.data[position].title
            Picasso.get().load(list.data[position].cover_medium).into(imageCategory)
            cardViewAlbumDetail.setOnClickListener {
               val data = artistDetailFragmentDirections.actionArtistDetailFragmentToAlbumFragment(list.data.get(position).id.toString())
                Navigation.findNavController(it).navigate(data)
            }


        }
    }
}