package com.tunahankaryagdi.wordmate.learned

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.tunahankaryagdi.wordmate.data.Word
import com.tunahankaryagdi.wordmate.databinding.FragmentLearnedBinding
import com.tunahankaryagdi.wordmate.learned.adapter.LearnedWordsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class LearnedFragment : Fragment() {

    private val viewModel by viewModels<LearnedViewModel>()

    private var _binding: FragmentLearnedBinding? = null
    private val binding get() = _binding!!

    private lateinit var learnedWordsAdapter: LearnedWordsAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLearnedBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        learnedWordsAdapter = LearnedWordsAdapter(onClickItem = ::onClickItem)
        binding.rvLearnedWordList.adapter = learnedWordsAdapter
        viewModel.getLearnedWords()
        observeUiState()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onClickItem(word: Word) {
        findNavController().navigate(
            LearnedFragmentDirections.actionLearnedFragmentToDetailDialogFragment(
                word,
                false
            )
        )
    }

    private fun observeUiState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiState.collect { state ->
                if (state.learnedWords.isEmpty()){
                    binding.rvLearnedWordList.visibility = View.INVISIBLE
                    binding.tvEmptyContent.visibility = View.VISIBLE
                }
                else{
                    binding.rvLearnedWordList.visibility = View.VISIBLE
                    binding.tvEmptyContent.visibility = View.INVISIBLE
                }
                learnedWordsAdapter.setData(state.learnedWords)
            }
        }
    }

}