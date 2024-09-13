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

    init {
        getUnlearnedWords()
        getLearnedWords()
    }

    fun getUnlearnedWords() {
        val learnedWords = wordRepository.getWords()
        val filteredWords = _uiState.value.unlearnedWords.filter { word ->
            !learnedWords.contains(word)
        }
        _uiState.update { it ->
            it.copy(unlearnedWords = filteredWords)
        }
    }

    fun shuffleWords() {
        val words = _uiState.value.unlearnedWords
        _uiState.update {
            it.copy(unlearnedWords = words.shuffled())
        }
    }

    fun getLearnedWords() {
        val words = wordRepository.getWords()
        _uiState.update {
            it.copy(learnedWords = words)
        }
    }

    fun saveWord(word: Word) {
        wordRepository.addWord(word)
        getUnlearnedWords()
        getLearnedWords()
        _uiState.update {
            it.copy(event = UiEvent.WordSaved)
        }

    }

    fun removeWord(word: Word) {
        wordRepository.removeWord(word)
        getLearnedWords()
        _uiState.update { it ->
            val updatedUnlearnedWords = it.unlearnedWords.toMutableList().apply {
                add(word)
            }
            it.copy(event = UiEvent.WordRemoved, unlearnedWords = updatedUnlearnedWords)
        }
    }

    fun clearEvent() {
        _uiState.update {
            it.copy(event = null)
        }
    }

    fun setUiStateForTesting(state: UiState) {
        _uiState.value = state
    }
}

data class UiState(
    val learnedWords: List<Word> = emptyList(),
    val unlearnedWords: List<Word> = getAllWords().shuffled(),
    val event: UiEvent? = null
)

sealed class UiEvent {
    object WordSaved : UiEvent()
    object WordRemoved : UiEvent()
}