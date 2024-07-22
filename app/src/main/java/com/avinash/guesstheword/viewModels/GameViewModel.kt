package com.avinash.guesstheword.viewModels

import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

    private var words: MutableList<String> = mutableListOf("Happy", "Sad", "Dance", "Walk", "Run")
    private var totalPoints: Int = 0
    private var currentWord: String? = null
    private var randomNumber: Int = 0

    fun setNewWord(): String {
        randomNumber = getRandomNumber()
        if (randomNumber >= 0) {
            currentWord = words[getRandomNumber()];
            words.remove(currentWord)
        } else currentWord = ""
        return currentWord ?: ""
    }

    fun updatePoint(addPoint: Boolean): Int {
        return if (addPoint)
            ++totalPoints
        else --totalPoints
    }

    private fun getRandomNumber(): Int {
        if (words.isNotEmpty())
            return (0 until words.size).random()
        else return -1
    }

    fun getCurrentPoint() = totalPoints

    fun getCurrentWord() = currentWord ?: setNewWord()
}