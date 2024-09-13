package com.tunahankaryagdi.wordmate.presentation.learned.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tunahankaryagdi.wordmate.data.Word
import com.tunahankaryagdi.wordmate.databinding.ItemLearnedWordListBinding

class LearnedWordsAdapter(
    val onClickItem: (Word) -> Unit
) : RecyclerView.Adapter<LearnedWordsViewHolder>() {

    private var words = listOf<Word>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LearnedWordsViewHolder {
        val binding =
            ItemLearnedWordListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LearnedWordsViewHolder(binding)
    }

    override fun getItemCount(): Int = words.size

    override fun onBindViewHolder(holder: LearnedWordsViewHolder, position: Int) {
        holder.bind(words[position], onClickItem)
    }

    fun setData(newData: List<Word>) {
        words = newData
        notifyDataSetChanged()
    }

}