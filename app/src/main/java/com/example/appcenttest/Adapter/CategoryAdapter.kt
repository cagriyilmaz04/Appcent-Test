package com.example.appcenttest.Adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.appcenttest.Model.GenresResponse
import com.example.appcenttest.R
import com.example.appcenttest.databinding.RecyclerRowBinding
import com.squareup.picasso.Picasso

class CategoryAdapter(val list:GenresResponse):RecyclerView.Adapter<CategoryAdapter.CategoryVH>() {
    class CategoryVH(val binding: RecyclerRowBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryVH {
        val binding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CategoryVH(binding)
    }

    override fun getItemCount(): Int {
        return list.data.size
    }

    override fun onBindViewHolder(holder: CategoryVH, position: Int) {
        holder.binding.apply {
            textCategoryName.text = list.data.get(position).name
            Picasso.get().load(list.data.get(position).picture_small).into(imageCategory)
            cardViewCategory.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_artistFragment)
            }

        }
    }
}