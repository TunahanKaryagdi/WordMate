package com.tunahankaryagdi.wordmate.learned.adapter

import androidx.recyclerview.widget.RecyclerView
import com.tunahankaryagdi.wordmate.data.Word
import com.tunahankaryagdi.wordmate.databinding.ItemLearnedWordListBinding

class LearnedWordsViewHolder(val binding: ItemLearnedWordListBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(word: String, onClickItem: (String) -> Unit) {
        with(binding){
            tvMain.text = word
            tvSecondary.text = word
            root.setOnClickListener {
                onClickItem(word)
            }
        }
    }
}