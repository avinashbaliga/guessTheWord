package com.avinash.guesstheword.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

    private var words: MutableList<String> = mutableListOf("Happy", "Sad", "Dance", "Walk", "Run")
    val totalPoints = MutableLiveData<Int>()
    val currentWord = MutableLiveData<String>()
    private var randomNumber: Int = 0

    init {
        totalPoints.value = 0
        setNewWord()
    }

    private fun setNewWord() {
        randomNumber = getRandomNumber()
        if (randomNumber >= 0) {
            currentWord.value = words[getRandomNumber()];
            words.remove(currentWord.value)
        } else currentWord.value = ""
    }

    fun incrementPoint() {
        setNewWord()
        if (currentWord.value != "")
            updatePoint(true)
    }

    fun decrementPoint() {
        updatePoint(false)
        setNewWord()
    }

    private fun updatePoint(addPoint: Boolean): Int {
        if (addPoint)
            totalPoints.value = totalPoints.value?.plus(1)
        else totalPoints.value = totalPoints.value?.minus(1)

        return totalPoints.value ?: 0
    }

    private fun getRandomNumber(): Int {
        return if (words.isNotEmpty())
            (0 until words.size).random()
        else -1
    }
}