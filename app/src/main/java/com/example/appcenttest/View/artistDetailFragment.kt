package com.example.appcenttest.View

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appcenttest.Adapter.AlbumDetailAdapter
import com.example.appcenttest.Repository.MainRepository
import com.example.appcenttest.Service.RetrofitApi
import com.example.appcenttest.ViewModel.AlbumViewModel
import com.example.appcenttest.ViewModel.AlbumViewModelFactory
import com.example.appcenttest.databinding.FragmentArtistDetailBinding
import com.squareup.picasso.Picasso


class artistDetailFragment : Fragment() {
    private var _binding: FragmentArtistDetailBinding?=null
    private val binding get() = _binding!!
    lateinit var viewModel: AlbumViewModel
    val args : artistDetailFragmentArgs by navArgs()
    lateinit var layoutManager:LinearLayoutManager
    override fun onAttach(context: Context) {
        super.onAttach(context)
        layoutManager = LinearLayoutManager(requireContext())
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentArtistDetailBinding.inflate(layoutInflater,container,false)
        binding.toolbarArtistDetail.title = homeFragment.artistName
        val retrofitService = RetrofitApi.getInstance()
        val mainRepository = MainRepository(retrofitService,args.artist.id.toString())
        Picasso.get().load(args.artist.pictureMedium).into(binding.imageViewArtistDetail)
        viewModel = ViewModelProvider(this, AlbumViewModelFactory(mainRepository)).get(AlbumViewModel::class.java)
        viewModel.albumDetail.observe(requireActivity()) {
            val adapter = AlbumDetailAdapter(viewModel.albumDetail.value!!)
            layoutManager.orientation = LinearLayoutManager.VERTICAL
            binding.recyclerViewArtistDetail.layoutManager = layoutManager
            binding.recyclerViewArtistDetail.adapter = adapter
        }
        viewModel.getAllAlbums()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

}