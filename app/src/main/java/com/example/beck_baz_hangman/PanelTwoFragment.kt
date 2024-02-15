package com.example.beck_baz_hangman


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PanelTwoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PanelTwoFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_panel_two, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var buttonCount : Int = 0;

        val button: Button = view.findViewById(R.id.hintButton)
        val textView: TextView = view.findViewById(R.id.hintText)
        textView.setText(MainActivity.hint_bank[MainActivity.current_word])

        button.setOnClickListener {_ ->
            buttonCount += 1
            if (buttonCount == 1) {
                textView.visibility = View.VISIBLE
            } else if (MainActivity.lives <= 2) {
                val zero = Toast.makeText(requireContext(), "Hint not available", Toast.LENGTH_SHORT)
                zero.show()
            } else if (buttonCount == 2) {
                val buttonMasterList = arrayListOf<Char>(
                    'a', 'b', 'c', 'd', 'e', 'f',
                    'g', 'h', 'i', 'j', 'k', 'l',
                    'm', 'n', 'o', 'p', 'q', 'r',
                    's', 't', 'u', 'v', 'w', 'x',
                    'y', 'z'
                )

                val cap: Int = (26 - MainActivity.disabled_buttons.size) / 2
                var counter: Int = 0
                val word = MainActivity.word_bank[MainActivity.current_word]

                for (charBtn in buttonMasterList) {
                    if (counter == cap) {
                        break
                    }

                    if (charBtn !in word && charBtn.uppercaseChar() !in MainActivity.disabled_buttons) {
                        MainActivity.disabled_buttons.add(charBtn.uppercaseChar())
                        counter += 1
                    }
                }
                MainActivity.lives -= 1

                // Render the now disabled buttons
                MainActivity.horz_buttons.forEach {btn ->
                    if (btn.text.toString().first() in MainActivity.disabled_buttons) {
                        btn.visibility = View.GONE // BYE
                    }
                }

                MainActivity.vert_buttons.forEach {btn ->
                    if (btn.text.toString().first() in MainActivity.disabled_buttons) {
                        btn.visibility = View.GONE // BYE
                    }
                }
            } else if (buttonCount == 3) {
                val buttonMasterList = arrayListOf<Char>(
                    'a', 'e', 'i', 'o', 'u'
                )
                val word = MainActivity.word_bank[MainActivity.current_word].toCharArray()
                for (charBtn in buttonMasterList) {
                    if (charBtn.uppercaseChar() !in MainActivity.disabled_buttons) {
                        MainActivity.disabled_buttons.add(charBtn.uppercaseChar())
                    }

                    for (charIdx in word.indices) {
                        val character = word[charIdx]
                        if (character == charBtn) {
                            MainActivity.textViewIndices.add(charIdx)
                        }
                    }
                }

                // Render the now disabled buttons
                MainActivity.horz_buttons.forEach {btn ->
                    if (btn.text.toString().first() in MainActivity.disabled_buttons) {
                        btn.visibility = View.GONE // BYE
                    }
                }

                MainActivity.vert_buttons.forEach {btn ->
                    if (btn.text.toString().first() in MainActivity.disabled_buttons) {
                        btn.visibility = View.GONE // BYE
                    }
                }

                // Render the correct vowels
                (activity as? MainActivity)?.updateVowels()
                MainActivity.lives -= 1
            }else {
                val zero = Toast.makeText(requireContext(), "Out of hints", Toast.LENGTH_SHORT)
                zero.show()
            }


            // Update the image
            (activity as? MainActivity)?.updateGraphics()
        }
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