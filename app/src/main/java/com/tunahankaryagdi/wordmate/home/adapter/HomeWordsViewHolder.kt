package com.tunahankaryagdi.wordmate.home.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.tunahankaryagdi.wordmate.R
import com.tunahankaryagdi.wordmate.databinding.ItemWordListBinding

class HomeWordsViewHolder(private val binding: ItemWordListBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(word: String, onClickItem: (String) -> Unit) {

        with(binding){
            tvMain.text = word
            tvSecondary.text = "My text"
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