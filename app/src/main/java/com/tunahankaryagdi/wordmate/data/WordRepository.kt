package com.tunahankaryagdi.wordmate.data


interface WordRepository {
    fun addWord(word: Word)
    fun getWords() : List<Word>
    fun removeWord(word: Word)
}