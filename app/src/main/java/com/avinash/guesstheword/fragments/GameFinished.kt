package com.avinash.guesstheword.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.avinash.guesstheword.databinding.FragmentGameFinishedBinding

class GameFinished : Fragment() {
    private lateinit var fragmentGameFinishedBinding: FragmentGameFinishedBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentGameFinishedBinding = FragmentGameFinishedBinding.inflate(layoutInflater)
        setTotalScore()
        return fragmentGameFinishedBinding.root
    }

    private fun setTotalScore() {
        fragmentGameFinishedBinding.totalScore.text =
            GameFinishedArgs.fromBundle(requireArguments()).totalPoints.toString()
    }
}