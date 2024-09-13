package com.tunahankaryagdi.wordmate.presentation.home.adapter

import androidx.recyclerview.widget.RecyclerView
import com.tunahankaryagdi.wordmate.data.Word
import com.tunahankaryagdi.wordmate.databinding.ItemWordListBinding

class HomeWordsViewHolder(private val binding: ItemWordListBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(word: Word, onClickItem: (Word) -> Unit) {

        with(binding){
            tvMain.text = word.turkish
            tvSecondary.text = word.english
            root.setOnClickListener {
                onClickItem(word)
            }

        }
    }
}