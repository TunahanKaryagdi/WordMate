package com.tunahankaryagdi.wordmate.detail

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs
import com.tunahankaryagdi.wordmate.R
import com.tunahankaryagdi.wordmate.databinding.FragmentDetailDialogBinding
import com.tunahankaryagdi.wordmate.databinding.FragmentLearnedBinding

class DetailDialogFragment : DialogFragment() {

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
                binding.btnLearned.setText(R.string.unlearned)
            }
        }


        return builder.create()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}