package com.tfg.elaapp.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Chronometer
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.tfg.elaapp.MainActivity
import com.tfg.elaapp.R
import com.tfg.elaapp.constants.*
import com.tfg.elaapp.db.sentences
import com.tfg.elaapp.dbmanagers.sentencemanager
import java.text.SimpleDateFormat
import java.util.*
import java.util.stream.Collectors

class Sentences : Fragment() {
    private lateinit var buttonPlay : ImageButton
    private lateinit var sentence : TextView
    private lateinit var crono : Chronometer
    private lateinit var buttonBack : Button
    private lateinit var set : MutableList<String>
    private var count = 0
    private var onRecord : Boolean = false
    private val random = Random()
    private var randomNumbers: List<Int> = random.ints(0, 14).distinct().limit(5).boxed().collect(Collectors.toList())

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_sentences, container, false)
        loadComponents(view)
        setUpListeners(view)
        return view
    }

    private fun loadComponents(view: View) {
        buttonPlay = view.findViewById(R.id.playrecorder)
        sentence = view.findViewById(R.id.text_sentence)
        crono = view.findViewById(R.id.cronometre)
        buttonBack = view.findViewById(R.id.buttonBackSentences)
        set = constants_sentences.sentences
    }

    private fun setUpListeners(view: View) {
        buttonBack.setOnClickListener {
            (activity as MainActivity).mainFrag()
        }

        buttonPlay.setOnClickListener {
            // ja estava apretat
            if (onRecord) {
                onRecord = false
                buttonPlay.setImageResource(R.drawable.play_circle)
                crono.stop()
                // inici del guardat
                saveData(crono, randomNumbers[count])

            }
            else {
                onRecord = true
                buttonPlay.setImageResource(R.drawable.stop_circle)
                crono.setBase(SystemClock.elapsedRealtime());
                sentence.setText(set[randomNumbers[count]])
                ++count
                if (count > 4) {
                    count = 0
                    randomNumbers = random.ints(0, 14).distinct().limit(5).boxed().collect(Collectors.toList())
                }
                crono.start()
            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun saveData(crono:Chronometer, num : Int ) {
        // pillar time
        val sharedPref = this.activity?.getPreferences(Context.MODE_PRIVATE)
        val usr = sharedPref!!.getString("NAME", "").toString()
        sentencemanager.addSentence(sentences(time = crono.text.toString(), sentence = count,
                user = usr, SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Calendar.getInstance().time)
        ))
    }


}