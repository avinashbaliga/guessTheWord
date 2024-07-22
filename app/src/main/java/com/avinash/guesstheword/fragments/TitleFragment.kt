package com.avinash.guesstheword.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.avinash.guesstheword.R
import com.avinash.guesstheword.databinding.FragmentTitleBinding

class TitleFragment : Fragment() {

    private lateinit var titleBinding: FragmentTitleBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        titleBinding = FragmentTitleBinding.inflate(layoutInflater)
        setPlayButtonListener();
        return titleBinding.root
    }

    private fun setPlayButtonListener() {
        titleBinding.playGameButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_titleFragment_to_gameFragment)
        }
    }
}