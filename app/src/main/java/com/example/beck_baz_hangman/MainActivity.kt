package com.example.beck_baz_hangman

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView






class MainActivity : AppCompatActivity() {
    private lateinit var hangmanImageView: ImageView
    private lateinit var btnIncorrectGuess: Button
    private var incorrectGuesses = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        hangmanImageView = findViewById(R.id.hangmanImageView)
        btnIncorrectGuess = findViewById(R.id.btnIncorrectGuess)

        btnIncorrectGuess.setOnClickListener {
            handleWrongGuess()
        }


    }
    private fun updateImage() {
        hangmanImageView.setImageResource(getImageResource())
    }

    private fun handleWrongGuess() {
        onWrongGuess()
        updateImage()
        if (isGameOver()) {
            // TODO: Show game over message
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
    private fun resetGame() {
        incorrectGuesses = 0
    }
    private fun onWrongGuess() {
        incorrectGuesses++
        if (incorrectGuesses > 6) {
            resetGame()
        }
    }

    private fun isGameOver(): Boolean {
        return incorrectGuesses > 6
    }


}


