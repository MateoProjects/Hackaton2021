package com.tfg.elaapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.tfg.elaapp.MainActivity
import com.tfg.elaapp.R


class mainFragment : Fragment() {
    private lateinit var sentencesButton : ImageView
    private lateinit var eatButton : ImageView
    private lateinit var metriquesButton: ImageView
    private lateinit var sendDataButton: ImageView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        loadComponents(view)
        setUpListeners(view)
        return view
    }

    fun loadComponents(view: View) {
        sentencesButton = view.findViewById(R.id.sentencesMain)
        eatButton = view.findViewById(R.id.eatMain)
        metriquesButton = view.findViewById(R.id.metriquesMain)
        sendDataButton = view.findViewById(R.id.sendDataMain)
    }
    fun setUpListeners(view: View) {
        sentencesButton.setOnClickListener {
            (activity as MainActivity).callSentences()
        }
        eatButton.setOnClickListener {
            (activity as MainActivity).callEat()
        }
        metriquesButton.setOnClickListener {
            (activity as MainActivity).callrtmetrics()

        }
        sendDataButton.setOnClickListener {
            (activity as MainActivity).callSendData()
        }
    }

}