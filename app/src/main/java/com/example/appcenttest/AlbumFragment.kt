package com.example.appcenttest

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appcenttest.Adapter.AlbumAdapter
import com.example.appcenttest.Adapter.AlbumDetailAdapter
import com.example.appcenttest.Repository.MainRepository
import com.example.appcenttest.ViewModel.AlbumDetailViewModel
import com.example.appcenttest.ViewModel.AlbumDetailViewModelFactory
import com.example.appcenttest.ViewModel.AlbumViewModel
import com.example.appcenttest.ViewModel.AlbumViewModelFactory
import com.example.appcenttest.ViewModel.TracksViewModel
import com.example.appcenttest.ViewModel.TracksViewModelFactory
import com.example.appcenttest.databinding.FragmentAlbumBinding
import com.squareup.picasso.Picasso


class AlbumFragment : Fragment() {
    private var _binding: FragmentAlbumBinding?=null
    lateinit var layoutManager : LinearLayoutManager
    private val binding get() = _binding!!
    lateinit var viewModel: AlbumDetailViewModel
    lateinit var tracksViewModel:TracksViewModel
    val args : AlbumFragmentArgs by navArgs()
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
        _binding = FragmentAlbumBinding.inflate(layoutInflater,container,false)
        tracksViewModel = ViewModelProvider(requireActivity()).get(TracksViewModel::class.java)
        val retrofitService = RetrofitApi.getInstance()
        val mainRepository = MainRepository(retrofitService,args.id)
        viewModel = ViewModelProvider(this, AlbumDetailViewModelFactory(mainRepository)).get(AlbumDetailViewModel::class.java)
        tracksViewModel = ViewModelProvider(this).get(TracksViewModel::class.java)
        viewModel.albumDetail.observe(requireActivity()) {

            binding.recyclerViewAlbums.layoutManager = layoutManager
            val adapter = AlbumAdapter(viewModel.albumDetail.value!!,tracksViewModel,requireContext())
            binding.recyclerViewAlbums.adapter = adapter
        }
        viewModel.getAlbumDetail()

        return binding.root
    }


}