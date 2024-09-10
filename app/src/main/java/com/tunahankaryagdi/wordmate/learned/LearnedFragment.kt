package com.tunahankaryagdi.wordmate.learned

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.tunahankaryagdi.wordmate.R


class LearnedFragment : Fragment() {

    private val viewModel by viewModels<LearnedViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_learned, container, false)
    }


}