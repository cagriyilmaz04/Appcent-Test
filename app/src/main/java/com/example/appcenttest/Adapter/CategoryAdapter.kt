package com.example.appcenttest.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.appcenttest.Model.GenresResponse
import com.example.appcenttest.databinding.RecyclerRowBinding
import com.example.appcenttest.homeFragmentDirections
import com.squareup.picasso.Picasso

class CategoryAdapter(val list:GenresResponse):RecyclerView.Adapter<CategoryAdapter.CategoryVH>() {
    class CategoryVH(val binding: RecyclerRowBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryVH {
        val binding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CategoryVH(binding)
    }

    override fun getItemCount(): Int {
        return list.data.size
    }

    override fun onBindViewHolder(holder: CategoryVH, position: Int) {
        holder.binding.apply {
            textCategoryName.text = list.data[position].name
            Picasso.get().load(list.data[position].pictureMedium).into(imageCategory)
            cardViewCategory.setOnClickListener {
            val data = list.data.get(position).id
            val action = homeFragmentDirections.actionHomeFragmentToArtistFragment(data.toString())
            Navigation.findNavController(it).navigate(action)
            }
        }
    }
}