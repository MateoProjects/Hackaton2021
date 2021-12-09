package com.tfg.elaapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.tfg.elaapp.MainActivity
import com.tfg.elaapp.R


class about : Fragment() {
    private lateinit var buttonBack : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_about, container, false)
        loadComponents(view)
        setUpListeners(view)
        return view
    }

    private fun loadComponents(view:View) {
        buttonBack = view.findViewById(R.id.about_button_back)
    }

    private fun setUpListeners(view:View) {
        buttonBack.setOnClickListener {
            (activity as MainActivity).mainFrag()
        }
    }

}