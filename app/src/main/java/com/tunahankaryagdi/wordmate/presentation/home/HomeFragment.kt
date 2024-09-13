package com.tunahankaryagdi.wordmate.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.tunahankaryagdi.wordmate.presentation.SharedViewModel
import com.tunahankaryagdi.wordmate.data.Word
import com.tunahankaryagdi.wordmate.databinding.FragmentHomeBinding
import com.tunahankaryagdi.wordmate.presentation.home.adapter.HomeWordsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel by activityViewModels<SharedViewModel>()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var homeWordsAdapter: HomeWordsAdapter

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
        viewModel.getUnlearnedWords()
        observeUiState()
        binding.srlRefreshLayout.setOnRefreshListener {
            viewModel.shuffleWords()
            binding.srlRefreshLayout.isRefreshing = false
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onClickItem(word: Word) {
        findNavController().navigate(
            HomeFragmentDirections.actionHomeFragmentToDetailDialogFragment(
                word,
                true
            )
        )
    }

    private fun observeUiState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiState.collect { state ->
                homeWordsAdapter.setData(state.learnedWords)
            }
        }
    }

}