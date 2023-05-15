package com.example.appcenttest.View

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appcenttest.Adapter.TrackAdapter
import com.example.appcenttest.Model.Tracks
import com.example.appcenttest.ViewModel.TracksViewModel
import com.example.appcenttest.databinding.FragmentFavoriteBinding

class favoriteFragment : Fragment(),TrackAdapter.TrackListener {
    private lateinit var viewModel:TracksViewModel
    private var _binding:FragmentFavoriteBinding?=null
    private val binding get() = _binding!!
    lateinit var layoutManager: LinearLayoutManager
    lateinit var adapter: TrackAdapter
    private var currentData = listOf<Tracks>()
    override fun onAttach(context: Context) {
        layoutManager = LinearLayoutManager(context)
        super.onAttach(context)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(layoutInflater,container,false)
        viewModel = ViewModelProvider(requireActivity()).get(TracksViewModel::class.java)
        binding.toolbarFavorite.title = "Favorite"
        viewModel.readAllData.observe(requireActivity()) {
            Log.e("TAG", viewModel.readAllData.value?.size.toString())
            binding.recyclerViewTrack.layoutManager = layoutManager
            val adapter = TrackAdapter(viewModel.readAllData.value!! as ArrayList<Tracks>,this)
            binding.recyclerViewTrack.adapter = adapter
            currentData = viewModel.readAllData.value!!
        }

        return binding.root
    }

    override fun onItemClick(value: Int) {
        if (value >= 0 && value < currentData.size) {
            viewModel.deleteTrack(currentData[value])
        }
    }


}