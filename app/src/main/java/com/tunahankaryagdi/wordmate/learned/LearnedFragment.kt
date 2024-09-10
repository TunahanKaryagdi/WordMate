package com.tunahankaryagdi.wordmate.learned

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.tunahankaryagdi.wordmate.R
import com.tunahankaryagdi.wordmate.databinding.FragmentHomeBinding
import com.tunahankaryagdi.wordmate.databinding.FragmentLearnedBinding


class LearnedFragment : Fragment() {

    private val viewModel by viewModels<LearnedViewModel>()

    private var _binding: FragmentLearnedBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLearnedBinding.inflate(inflater)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}