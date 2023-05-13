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
import com.example.appcenttest.Adapter.ArtistAdapter
import com.example.appcenttest.Adapter.CategoryAdapter
import com.example.appcenttest.Repository.MainRepository
import com.example.appcenttest.ViewModel.ArtistViewModel
import com.example.appcenttest.ViewModel.ArtistViewModelFactory
import com.example.appcenttest.ViewModel.MainViewModel
import com.example.appcenttest.ViewModel.MainViewModelFactory
import com.example.appcenttest.databinding.FragmentArtistBinding


class artistFragment : Fragment() {
    private var _binding : FragmentArtistBinding ? = null
    private val binding get() = _binding!!
    private val args: artistFragmentArgs by navArgs()
    lateinit var viewModel: ArtistViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentArtistBinding.inflate(layoutInflater,container,false)
        val retrofitService = RetrofitApi.getInstance()
        val string = args.id
        val mainRepository = MainRepository(retrofitService,string)
        viewModel = ViewModelProvider(this, ArtistViewModelFactory(mainRepository)).get(ArtistViewModel::class.java)
        viewModel.artistList.observe(requireActivity()) {
            val adapter = ArtistAdapter(viewModel.artistList.value!!)
            val layoutManager = GridLayoutManager(requireContext(), 2)
            binding.recyclerViewArtist.layoutManager = layoutManager
            binding.recyclerViewArtist.adapter = adapter
        }
        viewModel.getAllArtist()
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }


}