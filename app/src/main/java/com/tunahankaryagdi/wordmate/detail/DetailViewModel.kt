package com.tunahankaryagdi.wordmate.detail

import androidx.lifecycle.ViewModel
import com.tunahankaryagdi.wordmate.data.Word
import com.tunahankaryagdi.wordmate.data.WordRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: WordRepository
) : ViewModel() {


    fun saveWord(word: Word){
        repository.addWord(word)
    }

    fun removeWord(word: Word){
        repository.removeWord(word)
    }
}