package com.tunahankaryagdi.wordmate.learned

import androidx.lifecycle.ViewModel
import com.tunahankaryagdi.wordmate.data.Word
import com.tunahankaryagdi.wordmate.data.WordRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class LearnedViewModel @Inject constructor(
    private val wordRepository: WordRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<LearnedUiState> = MutableStateFlow(LearnedUiState())
    val uiState: StateFlow<LearnedUiState> get() = _uiState

    init {
        getLearnedWords()
    }

    private fun getLearnedWords() {
        val words = wordRepository.getWords()
        _uiState.update {
            it.copy(learnedWords = words)
        }
    }
}


data class LearnedUiState(
    val learnedWords: List<Word> = emptyList()
)