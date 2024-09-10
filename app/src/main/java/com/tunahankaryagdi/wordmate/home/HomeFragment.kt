package com.tunahankaryagdi.wordmate.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.tunahankaryagdi.wordmate.R
import com.tunahankaryagdi.wordmate.databinding.FragmentHomeBinding
import com.tunahankaryagdi.wordmate.databinding.FragmentLearnedBinding
import com.tunahankaryagdi.wordmate.home.adapter.HomeWordsAdapter


class HomeFragment : Fragment() {

    private val viewModel by viewModels<HomeViewModel>()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var homeWordsAdapter : HomeWordsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeWordsAdapter = HomeWordsAdapter()
        binding.rvWordList.adapter = homeWordsAdapter

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}