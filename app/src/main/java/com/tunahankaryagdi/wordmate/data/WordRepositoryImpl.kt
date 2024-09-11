package com.tunahankaryagdi.wordmate.data

import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject

class WordRepositoryImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val gson: Gson
) : WordRepository {

    companion object {
        private const val WORD_LIST_KEY = "word_list"
    }

    override fun addWord(word: Word) {
        val currentWords = getWords().toMutableList()
        if (!currentWords.contains(word)) {
            currentWords.add(word)
            saveWords(currentWords)
        }
    }

    override fun getWords(): List<Word> {
        val json = sharedPreferences.getString(WORD_LIST_KEY, null)
        val type = object : TypeToken<List<Word>>() {}.type
        return gson.fromJson(json, type) ?: emptyList()
    }

    override fun removeWord(word: Word) {
        val currentWords = getWords().toMutableList()
        if (currentWords.remove(word)) {
            saveWords(currentWords)
        }
    }

    private fun saveWords(words: List<Word>) {
        val json = gson.toJson(words)
        sharedPreferences.edit()
            .putString(WORD_LIST_KEY, json)
            .apply()
    }
}