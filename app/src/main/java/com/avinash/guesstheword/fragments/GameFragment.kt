package com.avinash.guesstheword.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
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
        setListeners()
        setLiveDataObservers()
        return gameBinding.root
    }

    private fun setListeners() {
        gameBinding.gotItButton.setOnClickListener {
            gameViewModel.incrementPoint()
        }

        gameBinding.skipButton.setOnClickListener {
            gameViewModel.decrementPoint()
        }
    }

    private fun setLiveDataObservers() {
        gameViewModel.totalPoints.observe(viewLifecycleOwner) { newScore ->
            gameBinding.scoreCard.text = newScore.toString()
        }

        gameViewModel.currentWord.observe(viewLifecycleOwner, Observer { newWord ->
            run {
                if (newWord == "") {
                    this.findNavController().navigate(
                        GameFragmentDirections.actionGameFragmentToGameFinished(
                            gameViewModel.totalPoints.value ?: 0
                        )
                    )
                } else {
                    gameBinding.wordTV.text = newWord
                }
            }
        })
    }
}