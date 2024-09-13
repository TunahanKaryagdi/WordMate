package com.tunahankaryagdi.wordmate.presentation.components

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.Toast
import com.tunahankaryagdi.wordmate.databinding.CustomToastBinding

class CustomToast(private val context: Context) {

    fun show(stringId: Int) {
        val inflater = LayoutInflater.from(context)
        val binding = CustomToastBinding.inflate(inflater)
        binding.tvMessage.setText(stringId)
        val toast = Toast(context)
        toast.duration = Toast.LENGTH_SHORT
        toast.view = binding.root
        toast.setGravity(Gravity.BOTTOM, 0, 300)
        toast.show()
    }
}