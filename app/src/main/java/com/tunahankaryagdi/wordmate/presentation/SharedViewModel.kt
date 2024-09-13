package com.tunahankaryagdi.wordmate.presentation

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
class SharedViewModel @Inject constructor(
    private val wordRepository: WordRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> get() = _uiState


    fun getUnlearnedWords() {
        val learnedWords = wordRepository.getWords()
        val filteredWords = getAllWords().filter { word ->
            !learnedWords.contains(word)
        }
        _uiState.update { it->
            it.copy(learnedWords = filteredWords)
        }
    }

    fun shuffleWords(){
        val words = _uiState.value.learnedWords
        _uiState.update {
            it.copy(learnedWords = words.shuffled())
        }
    }

    fun getLearnedWords() {
        val words = wordRepository.getWords()
        _uiState.update {
            it.copy(learnedWords = words)
        }
    }

    fun saveWord(word: Word){
        wordRepository.addWord(word)
        getUnlearnedWords()
    }

    fun removeWord(word: Word){
        wordRepository.removeWord(word)
        getLearnedWords()
    }
}

data class UiState(
    val learnedWords: List<Word> = emptyList(),
    val unlearnedWords: List<Word> = emptyList()
)