package com.example.beck_baz_hangman

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView



class HangmanGame {




    companion object {
        private var incorrectGuesses = 0
        fun getImageResource(): Int {
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
        fun resetGame() {
            incorrectGuesses = 0
        }
        fun onWrongGuess() {
            incorrectGuesses++
            if (incorrectGuesses > 6) {
                resetGame()
            }
        }



        fun isGameOver(): Boolean {
            return incorrectGuesses >= 6
        }
    }
}


class MainActivity : AppCompatActivity() {
    private lateinit var hangmanImageView: ImageView
    private lateinit var btnIncorrectGuess: ImageView
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
        hangmanImageView.setImageResource(HangmanGame.getImageResource())
    }

    private fun handleWrongGuess() {
        HangmanGame.onWrongGuess()
        updateImage()
        if (HangmanGame.isGameOver()) {
            // TODO: Show game over message
            HangmanGame.resetGame()
            updateImage()
        }
    }


}


