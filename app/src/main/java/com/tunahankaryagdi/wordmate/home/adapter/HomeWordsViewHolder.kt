package com.tunahankaryagdi.wordmate.home.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.tunahankaryagdi.wordmate.databinding.ItemWordListBinding

class HomeWordsViewHolder(private val binding: ItemWordListBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(word: String) {
        binding.tvMain.text = word
        binding.tvSecondary.text = "My text"

        binding.root.setOnClickListener {
            binding.tvSecondary.visibility =
                if (binding.tvSecondary.visibility == View.GONE) View.VISIBLE else View.GONE
        }
    }
}