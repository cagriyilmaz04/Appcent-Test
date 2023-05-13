package com.example.appcenttest

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appcenttest.Adapter.AlbumDetailAdapter
import com.example.appcenttest.Model.Album
import com.example.appcenttest.Model.AlbumResponse
import com.example.appcenttest.Repository.MainRepository
import com.example.appcenttest.ViewModel.AlbumViewModel
import com.example.appcenttest.ViewModel.AlbumViewModelFactory
import com.example.appcenttest.databinding.FragmentArtistDetailBinding
import com.squareup.picasso.Picasso


class artistDetailFragment : Fragment() {
    private var _binding: FragmentArtistDetailBinding?=null
    private val binding get() = _binding!!
    lateinit var viewModel: AlbumViewModel
    val args : artistDetailFragmentArgs by navArgs()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentArtistDetailBinding.inflate(layoutInflater,container,false)
        val retrofitService = RetrofitApi.getInstance()
        val mainRepository = MainRepository(retrofitService,args.artist.id.toString())
        Picasso.get().load(args.artist.picture_medium).into(binding.imageViewArtistDetail)
        viewModel = ViewModelProvider(this, AlbumViewModelFactory(mainRepository)).get(AlbumViewModel::class.java)
        viewModel.albumDetail.observe(requireActivity()) {
            val adapter = AlbumDetailAdapter(viewModel.albumDetail.value!!)
            val layoutManager = LinearLayoutManager(requireContext())
            layoutManager.orientation = LinearLayoutManager.VERTICAL
            binding.recyclerViewArtistDetail.layoutManager = layoutManager
            binding.recyclerViewArtistDetail.adapter = adapter
        }
        viewModel.getAllAlbums()

        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}