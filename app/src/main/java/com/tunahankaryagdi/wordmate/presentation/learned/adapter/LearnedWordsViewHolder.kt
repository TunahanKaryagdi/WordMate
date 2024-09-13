package com.tunahankaryagdi.wordmate.presentation.learned.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.tunahankaryagdi.wordmate.R
import com.tunahankaryagdi.wordmate.data.Word
import com.tunahankaryagdi.wordmate.databinding.ItemLearnedWordListBinding

class LearnedWordsViewHolder(val binding: ItemLearnedWordListBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(word: Word, onClickItem: (Word) -> Unit) {
        with(binding){
            tvMain.text = word.turkish
            tvSecondary.text = word.english
            ivArrow.setOnClickListener {
                if (tvSecondary.visibility == View.GONE){
                    tvSecondary.visibility = View.VISIBLE
                    ivArrow.setImageResource(R.drawable.ic_arrow_up)
                }
                else{
                    tvSecondary.visibility = View.GONE
                    ivArrow.setImageResource(R.drawable.ic_arrow_down)
                }
            }
            root.setOnClickListener {
                onClickItem(word)
            }
        }
    }
}