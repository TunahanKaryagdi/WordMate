package com.tunahankaryagdi.wordmate.detail

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.tunahankaryagdi.wordmate.R
import com.tunahankaryagdi.wordmate.databinding.FragmentDetailDialogBinding
import com.tunahankaryagdi.wordmate.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailDialogFragment : DialogFragment() {

    private val viewModel by viewModels<DetailViewModel>()

    private var _binding: FragmentDetailDialogBinding? = null
    private val binding get() = _binding!!

    private val args: DetailDialogFragmentArgs by navArgs()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder = AlertDialog.Builder(requireContext())

        val inflater = requireActivity().layoutInflater
        _binding = FragmentDetailDialogBinding.inflate(inflater)

        builder.setView(binding.root)

        with(binding){
            if (!args.primary){
                tvLearned.setText(R.string.unlearned)
                tvWarningText.setText(R.string.do_you_want_unlearned)
            }
            tvLearned.setOnClickListener {
                if (args.primary) viewModel.saveWord(args.word) else viewModel.removeWord(args.word)
            }
            tvTitle.text = args.word.turkish
            tvDescription.text = args.word.english
            tvCancel.setOnClickListener {
                dialog?.dismiss()
            }
        }
        return builder.create()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}