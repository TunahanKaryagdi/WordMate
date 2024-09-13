package com.tunahankaryagdi.wordmate.presentation.learned

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.tunahankaryagdi.wordmate.R
import com.tunahankaryagdi.wordmate.presentation.SharedViewModel
import com.tunahankaryagdi.wordmate.data.Word
import com.tunahankaryagdi.wordmate.databinding.FragmentLearnedBinding
import com.tunahankaryagdi.wordmate.presentation.UiEvent
import com.tunahankaryagdi.wordmate.presentation.components.CustomToast
import com.tunahankaryagdi.wordmate.presentation.learned.adapter.LearnedWordsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class LearnedFragment : Fragment() {

    private val viewModel by activityViewModels<SharedViewModel>()

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
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiState.collect { state ->
                if (state.event == UiEvent.WordRemoved){
                    CustomToast(requireContext()).show(R.string.removed_successfully)
                    viewModel.clearEvent()
                }
            }
        }
    }

}