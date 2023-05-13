package com.example.appcenttest

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.appcenttest.databinding.FragmentArtistDetailBinding


class artistDetailFragment : Fragment() {
    private var _binding: FragmentArtistDetailBinding?=null
    private val binding get() = _binding!!
    private val args: artistDetailFragmentArgs by navArgs()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentArtistDetailBinding.inflate(layoutInflater,container,false)

        Log.e("TAG",args.artist.toString())
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}