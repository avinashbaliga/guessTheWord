package com.avinash.guesstheword.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.avinash.guesstheword.R
import com.avinash.guesstheword.databinding.FragmentGameBinding
import com.avinash.guesstheword.viewModels.GameViewModel

class GameFragment : Fragment() {

    private lateinit var gameViewModel: GameViewModel
    private lateinit var gameBinding: FragmentGameBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        gameBinding = FragmentGameBinding.inflate(layoutInflater)
        gameViewModel = ViewModelProvider(this).get(GameViewModel::class.java)
        initializePointsAndWord()
        setListeners()
        return gameBinding.root
    }

    private fun setListeners() {
        gameBinding.gotItButton.setOnClickListener {
            setWordAndPoints(gameViewModel.setNewWord(), gameViewModel.updatePoint(true))
        }

        gameBinding.skipButton.setOnClickListener {
            setWordAndPoints(gameViewModel.setNewWord(), gameViewModel.updatePoint(false))
        }
    }

    private fun setWordAndPoints(word: String, point: Int) {

        println("Current word: $word")

        if (word == "") {
            this.findNavController().navigate(GameFragmentDirections.actionGameFragmentToGameFinished(gameViewModel.getCurrentPoint()))
        } else {
            gameBinding.wordTV.text = word
            gameBinding.scoreCard.text = point.toString()
        }
    }

    private fun initializePointsAndWord() {
        setWordAndPoints(gameViewModel.getCurrentWord(), gameViewModel.getCurrentPoint())
    }
}