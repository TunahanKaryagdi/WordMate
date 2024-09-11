package com.tunahankaryagdi.wordmate.learned

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.tunahankaryagdi.wordmate.R
import com.tunahankaryagdi.wordmate.databinding.FragmentHomeBinding
import com.tunahankaryagdi.wordmate.databinding.FragmentLearnedBinding
import com.tunahankaryagdi.wordmate.home.HomeFragmentDirections
import com.tunahankaryagdi.wordmate.home.adapter.HomeWordsAdapter
import com.tunahankaryagdi.wordmate.learned.adapter.LearnedWordsAdapter
import dagger.hilt.android.AndroidEntryPoint


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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onClickItem(word:String){
        findNavController().navigate(LearnedFragmentDirections.actionLearnedFragmentToDetailDialogFragment(word,false))
    }

}