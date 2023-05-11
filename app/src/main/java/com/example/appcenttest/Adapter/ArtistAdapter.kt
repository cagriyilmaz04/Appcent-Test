package com.example.appcenttest.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.appcenttest.Model.ArtistsResponse
import com.example.appcenttest.R
import com.example.appcenttest.databinding.RecyclerArtistBinding
import com.squareup.picasso.Picasso

class ArtistAdapter (val list: ArtistsResponse): RecyclerView.Adapter<ArtistAdapter.ArtistVH>() {
    class ArtistVH(val binding: RecyclerArtistBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistVH {
        val binding = RecyclerArtistBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ArtistVH(binding)
    }

    override fun getItemCount(): Int {
        return list.data.size
    }

    override fun onBindViewHolder(holder: ArtistVH, position: Int) {
        holder.binding.apply {
            textArtistName.text = list.data.get(position).name
            Picasso.get().load(list.data.get(position).picture_small).into(imageCategory)
            cardViewArtist.setOnClickListener {
                Navigation.findNavController(it).navigate(R.id.action_artistFragment_to_artistDetailFragment)

            }

        }
    }
}