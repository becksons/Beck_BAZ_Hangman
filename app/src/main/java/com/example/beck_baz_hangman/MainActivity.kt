package com.example.beck_baz_hangman

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.Fragment

interface FragmentCom {
    fun updateGraphics()
    fun updateVowels()
}

class MainActivity : AppCompatActivity(), FragmentCom {
    override fun updateGraphics() {
        val fragmentB = supportFragmentManager.findFragmentById(R.id.fragment_graphics) as? HangmanFragment
        fragmentB?.updateImage()
    }

    override fun updateVowels() {
        val fragmentB = supportFragmentManager.findFragmentById(R.id.fragment_graphics) as? HangmanFragment
        fragmentB?.handleCorrectGuess()
    }

    companion object{
        var lives = 7
        var disable_half = false
        var disable_vowels = false
        var show_vowels = false
        var hint_bank = arrayOf("Fruit","Planet","You live there","Flowing water","Happy")
        var textViewIndices = mutableListOf<Int>()
        val word_bank = arrayOf("apple", "earth", "house", "river", "smile")
        var current_word = word_bank.indices.random()
        var disabled_buttons = mutableListOf<Char>()
        var horz_buttons = arrayOf<Button>()
        var vert_buttons = arrayOf<Button>()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val hangmanFragment = HangmanFragment()
        val panelTwo = PanelTwoFragment()
        val buttonView = ButtonLogic()
        val buttonViewLand = ButtonLogicLand()

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragment_graphics, hangmanFragment)

                if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                    replace(R.id.horizontal_button_view, buttonView)
                } else if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    replace(R.id.fragment_hint,panelTwo)
                    replace(R.id.vertical_button_view, buttonViewLand)
                }
                commit()
            }
        }
    }

    fun startOver(newGame: Boolean) {
        textViewIndices = mutableListOf<Int>() // Clear rendering for words
        lives = 7 // Restore lives
        if (newGame) {
            current_word = MainActivity.word_bank.indices.random() // New Word (Duplicates may happen)
        }
        disabled_buttons = mutableListOf<Char>() // We reset the disable buttons

        val hangmanFragment = HangmanFragment()
        val panelTwo = PanelTwoFragment()
        val buttonView = ButtonLogic()
        val buttonViewLand = ButtonLogicLand()

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_graphics, hangmanFragment)

            if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                replace(R.id.horizontal_button_view, buttonView)
            } else if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                replace(R.id.fragment_hint,panelTwo)
                replace(R.id.vertical_button_view, buttonViewLand)
            }
            commit()
        }
    }

}
