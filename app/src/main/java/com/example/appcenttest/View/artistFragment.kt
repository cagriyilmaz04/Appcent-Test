package com.example.appcenttest.View

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.appcenttest.Adapter.ArtistAdapter
import com.example.appcenttest.Repository.MainRepository
import com.example.appcenttest.Service.RetrofitApi
import com.example.appcenttest.ViewModel.ArtistViewModel
import com.example.appcenttest.ViewModel.ArtistViewModelFactory
import com.example.appcenttest.databinding.FragmentArtistBinding


class artistFragment : Fragment() {
    private var _binding : FragmentArtistBinding ? = null
    private val binding get() = _binding!!
    private val args: artistFragmentArgs by navArgs()
    lateinit var viewModel: ArtistViewModel
    lateinit var layoutManager : GridLayoutManager
    override fun onAttach(context: Context) {
        layoutManager = GridLayoutManager(context,2)
        super.onAttach(context)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentArtistBinding.inflate(layoutInflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val retrofitService = RetrofitApi.getInstance()
        val string = args.id
        binding.toolbarArtist.title = homeFragment.categoryName
        val mainRepository = MainRepository(retrofitService,string)
        viewModel = ViewModelProvider(this, ArtistViewModelFactory(mainRepository)).get(ArtistViewModel::class.java)
        viewModel.artistList.observe(requireActivity()) {
            val adapter = ArtistAdapter(viewModel.artistList.value!!)
            binding.recyclerViewArtist.adapter = adapter
            binding.recyclerViewArtist.layoutManager = layoutManager
        }
        viewModel.getAllArtist()
        super.onViewCreated(view, savedInstanceState)
    }
    override fun onDestroyView() {
        super.onDestroyView()
    }


}