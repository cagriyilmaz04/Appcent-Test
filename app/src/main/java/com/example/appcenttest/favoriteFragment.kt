package com.example.appcenttest

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appcenttest.Adapter.TrackAdapter
import com.example.appcenttest.ViewModel.TracksViewModel
import com.example.appcenttest.databinding.FragmentFavoriteBinding

class favoriteFragment : Fragment() {
    private lateinit var viewModel:TracksViewModel
    private var _binding:FragmentFavoriteBinding?=null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(layoutInflater,container,false)
        viewModel = ViewModelProvider(requireActivity()).get(TracksViewModel::class.java)

        viewModel.readAllData.observe(requireActivity(),{
            Log.e("TAG",viewModel.readAllData.value?.size.toString())
            val layoutManager = LinearLayoutManager(requireContext())
            binding.recyclerViewTrack.layoutManager = layoutManager
            val adapter = TrackAdapter(viewModel.readAllData.value!!,viewModel,requireContext())
            binding.recyclerViewTrack.adapter = adapter
        })

        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()

    }

}