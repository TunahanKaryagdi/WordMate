package com.tunahankaryagdi.wordmate.home

import androidx.lifecycle.ViewModel
import com.tunahankaryagdi.wordmate.data.Word
import com.tunahankaryagdi.wordmate.data.WordRepository
import com.tunahankaryagdi.wordmate.data.getAllWords
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val wordRepository: WordRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> get() = _uiState


    fun getUnlearnedWords() {
        val learnedWords = wordRepository.getWords()
        val filteredWords = getAllWords().filter { word ->
            !learnedWords.contains(word)
        }
        _uiState.update { it->
            it.copy(wordList = filteredWords)
        }
    }

    fun shuffleWords(){
        val words = _uiState.value.wordList
        _uiState.update {
            it.copy(wordList = words.shuffled())
        }
    }

}


data class HomeUiState(
    val wordList: List<Word> = emptyList()
)