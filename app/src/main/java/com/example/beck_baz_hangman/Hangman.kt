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
import androidx.fragment.app.FragmentManager

class HangmanFragment : Fragment() {
    private lateinit var hangmanImageView: ImageView
    private lateinit var btnIncorrectGuess: Button
    private var fragmentView: View? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.hangman_visual, container, false)

        val resetButton = view.findViewById<Button>(R.id.Reset)
        val newGameButton = view.findViewById<Button>(R.id.NewGame)

        resetButton.setOnClickListener {
            (activity as? MainActivity)?.startOver(false)
        }

        newGameButton.setOnClickListener {
            (activity as? MainActivity)?.startOver(true)
        }

        fragmentView = view
        hangmanImageView = view.findViewById(R.id.hangmanImageView)

        if (savedInstanceState != null) {
            updateImage()
        }

        if(MainActivity.lives <= 1){
            handleGameOver()
        }
        handleCorrectGuess()
        return view
    }


    fun updateImage() {
        hangmanImageView.setImageResource(android.R.color.transparent)
        hangmanImageView.setImageResource(getImageResource())
    }

     fun handleCorrectGuess(){
        fragmentView?.let { view ->
            val wordIndex: MutableList<Int> = MainActivity.textViewIndices
            val currentWord = MainActivity.word_bank[MainActivity.current_word].toCharArray()

            for(charIdx in currentWord.indices){
                if (charIdx in wordIndex) {
                    val char: Char = currentWord[charIdx]

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

            if (MainActivity.textViewIndices.size == 5) {
                handleGameWin()
            }
        }
    }

    private fun handleGameWin() {
        // Disable all the Buttons
        MainActivity.disabled_buttons = mutableListOf(
            'A', 'B', 'C', 'D', 'E', 'F',
            'G', 'H', 'I', 'J', 'K', 'L',
            'M', 'N', 'O', 'P', 'Q', 'R',
            'S', 'T', 'U', 'V', 'W', 'X',
            'Y', 'Z'
        )

        MainActivity.horz_buttons.forEach {btn ->
            btn.visibility = View.GONE // BYE
        }

        MainActivity.vert_buttons.forEach {btn ->
            btn.visibility = View.GONE // BYE
        }

        fragmentView?.let { view ->
            val textDisplay = view.findViewById<TextView>(R.id.textDisplay)
            textDisplay.setText("You Won!")
        }
    }
    private fun handleGameOver(){
        // Disable all the Buttons
        MainActivity.disabled_buttons = mutableListOf(
            'A', 'B', 'C', 'D', 'E', 'F',
            'G', 'H', 'I', 'J', 'K', 'L',
            'M', 'N', 'O', 'P', 'Q', 'R',
            'S', 'T', 'U', 'V', 'W', 'X',
            'Y', 'Z'
        )

        MainActivity.horz_buttons.forEach {btn ->
            btn.visibility = View.GONE // BYE
        }

        MainActivity.vert_buttons.forEach {btn ->
            btn.visibility = View.GONE // BYE
        }

        fragmentView?.let { view ->
            val textDisplay = view.findViewById<TextView>(R.id.textDisplay)
            textDisplay.setText("Game over!")
        }
    }
    fun handleWrongGuess() {
        println("Lives:" + MainActivity.lives)
        MainActivity.lives -= 1
        updateImage()
        if (MainActivity.lives == 1) {
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
        MainActivity.disabled_buttons = mutableListOf<Char>() // We reset the disable buttons
        MainActivity.textViewIndices = mutableListOf<Int>() // Clear rendering for words
        MainActivity.lives = 7 // Restore lives

    }

}
