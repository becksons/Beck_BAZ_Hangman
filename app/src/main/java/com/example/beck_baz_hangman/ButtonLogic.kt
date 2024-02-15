package com.example.beck_baz_hangman


import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PanelTwoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ButtonLogic : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.horizontal_buttons, container, false)
        val hangmanFragment = parentFragmentManager.findFragmentById(R.id.fragment_graphics) as? HangmanFragment

        val letterA = view.findViewById<Button>(R.id.horz_button_A)
        val letterB = view.findViewById<Button>(R.id.horz_button_B)
        val letterC = view.findViewById<Button>(R.id.horz_button_C)
        val letterD = view.findViewById<Button>(R.id.horz_button_D)
        val letterE = view.findViewById<Button>(R.id.horz_button_E)
        val letterF = view.findViewById<Button>(R.id.horz_button_F)
        val letterG = view.findViewById<Button>(R.id.horz_button_G)
        val letterH = view.findViewById<Button>(R.id.horz_button_H)
        val letterI = view.findViewById<Button>(R.id.horz_button_I)
        val letterJ = view.findViewById<Button>(R.id.horz_button_J)
        val letterK = view.findViewById<Button>(R.id.horz_button_K)
        val letterL = view.findViewById<Button>(R.id.horz_button_L)
        val letterM = view.findViewById<Button>(R.id.horz_button_M)
        val letterN = view.findViewById<Button>(R.id.horz_button_N)
        val letterO = view.findViewById<Button>(R.id.horz_button_O)
        val letterP = view.findViewById<Button>(R.id.horz_button_P)
        val letterQ = view.findViewById<Button>(R.id.horz_button_Q)
        val letterR = view.findViewById<Button>(R.id.horz_button_R)
        val letterS = view.findViewById<Button>(R.id.horz_button_S)
        val letterT = view.findViewById<Button>(R.id.horz_button_T)
        val letterU = view.findViewById<Button>(R.id.horz_button_U)
        val letterV = view.findViewById<Button>(R.id.horz_button_V)
        val letterW = view.findViewById<Button>(R.id.horz_button_W)
        val letterX = view.findViewById<Button>(R.id.horz_button_X)
        val letterY = view.findViewById<Button>(R.id.horz_button_Y)
        val letterZ = view.findViewById<Button>(R.id.horz_button_Z)

        val btnList = arrayOf(
            letterA, letterB, letterC, letterD, letterE,
            letterF, letterG, letterH, letterI, letterJ,
            letterK, letterL, letterM, letterN, letterO,
            letterP, letterQ, letterR, letterS, letterT,
            letterU, letterV, letterW, letterX, letterY,
            letterZ
        )
        MainActivity.vert_buttons = btnList

        // We loop to disable any buttons
        for (btn in btnList) {
            val buttonText = btn.text.toString().first()
            if (buttonText in MainActivity.disabled_buttons) {
                btn.visibility = View.GONE // BYE
            }
        }

        for (btn in btnList) {
            btn.setOnClickListener(){ buttonView ->
                val button = buttonView as Button
                val buttonText = button.text.toString().lowercase().first()
                val currentWord = MainActivity.word_bank[MainActivity.current_word].toCharArray()
                var guessRight: Boolean = false

                button.isClickable = false
                button.visibility = View.GONE // BYE

                // Add the disabled char to a global state
                MainActivity.disabled_buttons.add(button.text.toString().first())

                for(charIdx in currentWord.indices){
                    if(buttonText == currentWord[charIdx] ){
                        MainActivity.textViewIndices.add(charIdx)
                        guessRight = true

                    }
                }
                if(!guessRight){
                    hangmanFragment?.handleWrongGuess()
                }else{
                    hangmanFragment?.handleCorrectGuess()

                }

            }
        }

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PanelTwoFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PanelTwoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}