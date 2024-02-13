package com.example.beck_baz_hangman

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment

class HangmanFragment : Fragment() {
    private lateinit var hangmanImageView: ImageView
    private lateinit var btnIncorrectGuess: Button
    private var incorrectGuesses = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.hangman_visual, container, false)

        hangmanImageView = view.findViewById(R.id.hangmanImageView)
        btnIncorrectGuess = view.findViewById(R.id.btnIncorrectGuess)

        btnIncorrectGuess.setOnClickListener {
            handleWrongGuess()
        }

        if (savedInstanceState != null) {
            incorrectGuesses = savedInstanceState.getInt("incorrectGuesses", 0)
            updateImage()
        }

        return view
    }


    private fun updateImage() {
        hangmanImageView.setImageResource(getImageResource())
    }

//Asked chatGPT what method to save the state of my fragment if the phone changes layout, back button is hit, etc..
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt("incorrectGuesses", incorrectGuesses)
    }

    private fun handleWrongGuess() {
        onWrongGuess()
        updateImage()
        if (isGameOver()) {
            // TODO: Show game over message/Handle game over



            resetGame()
            updateImage()
        }
    }

    private fun getImageResource(): Int {
        return when (incorrectGuesses) {
            0 -> R.drawable.hangman_0
            1 -> R.drawable.hangman_1
            2 -> R.drawable.hangman_2
            3 -> R.drawable.hangman_3
            4 -> R.drawable.hangman_4
            5 -> R.drawable.hangman_5
            6 -> R.drawable.hangman_6
            else -> R.drawable.hangman_0
        }
    }



    private fun onWrongGuess() {
        incorrectGuesses++
        if (incorrectGuesses > 6) {
            resetGame()
        }
    }
    private fun resetGame() {
        incorrectGuesses = 0
    }

    private fun isGameOver(): Boolean {
        return incorrectGuesses > 6
    }
}
