package com.example.beck_baz_hangman

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    companion object{
        var lives = 7
        var disable_half = false
        var disable_vowels = false
        var show_vowels = false
        var hint_bank = arrayOf("Fruit","Planet","You live there","Flowing water","Happy")
        var textViewIndices = mutableListOf<Int>()
        val word_bank = arrayOf("apple", "earth", "house", "river", "smile")
        val current_word = word_bank.indices.random()

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
                add(R.id.fragment_graphics, hangmanFragment)

                if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                    add(R.id.horizontal_button_view, buttonView)
                } else if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    add(R.id.fragment_hint,panelTwo)
                    add(R.id.vertical_button_view, buttonViewLand)
                }

                commit()
            }
        }


//        if (savedInstanceState == null) {
//            supportFragmentManager.beginTransaction().apply {
//                 add(R.id.fragment_graphics,hangmanFragment)
//                 add(R.id.fragment_hint,panelTwo)
////                 add(R.id.horizontal_button_view, buttonView)
//                 add(R.id.vertical_button_view, buttonViewLand)
//                 commit()
//
//            }
//
//        }
    }

}
