package com.tunahankaryagdi.wordmate.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tunahankaryagdi.wordmate.data.Word
import com.tunahankaryagdi.wordmate.databinding.ItemWordListBinding

class HomeWordsAdapter(
    val onClickItem: (Word) -> Unit
) : RecyclerView.Adapter<HomeWordsViewHolder>() {

    private var words: List<Word> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeWordsViewHolder {
        val binding =
            ItemWordListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeWordsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeWordsViewHolder, position: Int) {
        val word = words[position]
        holder.bind(word, onClickItem)
    }

    override fun getItemCount(): Int = words.size

    fun setData(newData: List<Word>) {
        words = newData
        notifyDataSetChanged()
    }


}