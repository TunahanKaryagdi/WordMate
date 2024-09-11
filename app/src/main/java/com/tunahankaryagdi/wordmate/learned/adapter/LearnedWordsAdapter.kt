package com.tunahankaryagdi.wordmate.learned.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tunahankaryagdi.wordmate.databinding.ItemLearnedWordListBinding

class LearnedWordsAdapter(
    val onClickItem: (String) -> Unit
)  : RecyclerView.Adapter<LearnedWordsViewHolder>() {

    private var words = listOf("GO", "CAN", "COME")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LearnedWordsViewHolder {
        val binding = ItemLearnedWordListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return LearnedWordsViewHolder(binding)
    }

    override fun getItemCount(): Int = words.size

    override fun onBindViewHolder(holder: LearnedWordsViewHolder, position: Int) {
        holder.bind(words[position],onClickItem)
    }
}