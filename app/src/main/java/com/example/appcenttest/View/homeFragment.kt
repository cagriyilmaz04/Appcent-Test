package com.example.appcenttest.View

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.appcenttest.Adapter.CategoryAdapter
import com.example.appcenttest.Repository.MainRepository
import com.example.appcenttest.Service.RetrofitApi
import com.example.appcenttest.ViewModel.MainViewModel
import com.example.appcenttest.ViewModel.MainViewModelFactory
import com.example.appcenttest.databinding.FragmentHomeBinding

class homeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        binding.toolbar.title = "Home"
        val retrofitService = RetrofitApi.getInstance()
        val mainRepository = MainRepository(retrofitService)

        viewModel = ViewModelProvider(this, MainViewModelFactory(mainRepository)).get(MainViewModel::class.java)
        viewModel.genreList.observe(requireActivity()) {
            val adapter = CategoryAdapter(viewModel.genreList.value!!)
            val layoutManager = GridLayoutManager(requireContext(), 2)
            binding.recyclerCategory.layoutManager = layoutManager


            binding.recyclerCategory.adapter = adapter
        }
        viewModel.getAllGenres()

        return binding.root
    }

    override fun onDestroyView() {
       _binding = null
        super.onDestroyView()
    }
    companion object{
        var categoryName = ""
        var artistName = ""
        var albumName = ""

    }
}