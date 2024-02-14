package com.example.beck_baz_hangman

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment

class HangmanFragment : Fragment() {
    private lateinit var hangmanImageView: ImageView
    private lateinit var btnIncorrectGuess: Button
    private var fragmentView: View? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.hangman_visual, container, false)

        fragmentView = view

        hangmanImageView = view.findViewById(R.id.hangmanImageView)
//        btnIncorrectGuess = view.findViewById(R.id.btnIncorrectGuess)
//
//        btnIncorrectGuess.setOnClickListener {
//            handleWrongGuess()
//        }

        if (savedInstanceState != null) {
            updateImage()
        }

        return view
    }


    private fun updateImage() {
        hangmanImageView.setImageResource(android.R.color.transparent)

        hangmanImageView.setImageResource(getImageResource())



    }

//Asked chatGPT what method to save the state of my fragment if the phone changes layout, back button is hit, etc..
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }


     fun handleCorrectGuess(){
        fragmentView?.let { view ->
            val wordIndex: MutableList<Int> = MainActivity.textViewIndices
            val currentWord = MainActivity.word_bank[MainActivity.current_word].toCharArray()

            for(charIdx in currentWord.indices){
                if (charIdx in wordIndex) {
                    val char: Char = currentWord[charIdx]

                    println("Char index:" + charIdx)

                    when(charIdx) {
                        0 -> {
                            view.findViewById<TextView>(R.id.LetterOne).setText(char.toString())
                        }
                        1 -> {
                            view.findViewById<TextView>(R.id.LetterTwo).setText(char.toString())
                        }
                        2 -> {
                            view.findViewById<TextView>(R.id.LetterThree).setText(char.toString())
                        }
                        3 -> {
                            view.findViewById<TextView>(R.id.LetterFour).setText(char.toString())
                        }
                        4 -> {
                            view.findViewById<TextView>(R.id.LetterFive).setText(char.toString())
                        }
                    }
                }
            }
        }
    }

    fun handleGameOver(){
        fragmentView?.let { view ->
            val textDisplay = view.findViewById<TextView>(R.id.textDisplay)
            textDisplay.setText("Game over!")


            }
        resetGame()


    }
    fun handleWrongGuess() {
        println("Lives:" + MainActivity.lives)
        MainActivity.lives -= 1
        updateImage()
        if (MainActivity.lives == 1) {
            // TODO: Show game over message/Handle game over
            // resetGame()
            handleGameOver()
        }
    }

    private fun getImageResource(): Int {

        return when (MainActivity.lives) {
            7 -> R.drawable.hangman_0
            6 -> R.drawable.hangman_1
            5 -> R.drawable.hangman_2
            4 -> R.drawable.hangman_3
            3 -> R.drawable.hangman_4
            2 -> R.drawable.hangman_5
            1 -> R.drawable.hangman_6
            else -> R.drawable.hangman_0
        }
    }

    private fun resetGame() {
        MainActivity.textViewIndices = mutableListOf<Int>()

    }

}
