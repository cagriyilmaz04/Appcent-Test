package com.example.appcenttest.View

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appcenttest.Adapter.AlbumAdapter
import com.example.appcenttest.Repository.MainRepository
import com.example.appcenttest.Service.RetrofitApi
import com.example.appcenttest.ViewModel.AlbumDetailViewModel
import com.example.appcenttest.ViewModel.AlbumDetailViewModelFactory
import com.example.appcenttest.ViewModel.TracksViewModel
import com.example.appcenttest.databinding.FragmentAlbumBinding


class AlbumFragment : Fragment(),AlbumAdapter.Listener {
    private var _binding: FragmentAlbumBinding?=null
    lateinit var layoutManager : LinearLayoutManager
    private val binding get() = _binding!!
    lateinit var viewModel: AlbumDetailViewModel
    lateinit var tracksViewModel:TracksViewModel
    val args : AlbumFragmentArgs by navArgs()
    lateinit var adapter:AlbumAdapter

    override fun onAttach(context: Context) {
        layoutManager = LinearLayoutManager(context)
        adapter = AlbumAdapter(null,context,this)
        super.onAttach(context)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    @SuppressLint("NotifyDataSetChanged")
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
            adapter.list = viewModel.albumDetail.value
            binding.recyclerViewAlbums.layoutManager = layoutManager
            binding.recyclerViewAlbums.adapter = adapter
            binding.recyclerViewAlbums.adapter!!.notifyDataSetChanged()
        }
        viewModel.getAlbumDetail()

        return binding.root
    }

    override fun onItemClick(value: Int) {
        var flag = false
        viewModel.albumDetail.observe(requireActivity()) {
            tracksViewModel.readAllData.observe(this){
                for(i in tracksViewModel.readAllData.value!!){
                    if(i.link.equals(viewModel.albumDetail.value!!.tracks.data.get(value).link)){
                            flag = true
                    }
                }
                if(!flag){
                    tracksViewModel.addTrack(viewModel.albumDetail.value!!.tracks.data.get(value))
                }
            }

        }
    }


}