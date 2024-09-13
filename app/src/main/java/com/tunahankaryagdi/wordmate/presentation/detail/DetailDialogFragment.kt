package com.tunahankaryagdi.wordmate.presentation.detail

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.tunahankaryagdi.wordmate.R
import com.tunahankaryagdi.wordmate.presentation.SharedViewModel
import com.tunahankaryagdi.wordmate.databinding.FragmentDetailDialogBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailDialogFragment() : DialogFragment() {

    private val viewModel by activityViewModels<SharedViewModel>()

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
                dialog?.dismiss()
            }
            tvTitle.text = args.word.english
            tvDescription.text = args.word.turkish
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