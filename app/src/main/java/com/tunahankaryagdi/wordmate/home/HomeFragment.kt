package com.tunahankaryagdi.wordmate.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.tunahankaryagdi.wordmate.R
import com.tunahankaryagdi.wordmate.databinding.FragmentHomeBinding
import com.tunahankaryagdi.wordmate.databinding.FragmentLearnedBinding
import com.tunahankaryagdi.wordmate.home.adapter.HomeWordsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
        homeWordsAdapter = HomeWordsAdapter(onClickItem = ::onClickItem)
        binding.rvWordList.adapter = homeWordsAdapter

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onClickItem(word:String){
        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailDialogFragment(word,true))
    }

}