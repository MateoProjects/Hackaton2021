package com.example.psicologiaapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [registre.newInstance] factory method to
 * create an instance of this fragment.
 */
class registre : Fragment() {
    lateinit var nom: EditText
    lateinit var cognom: EditText
    lateinit var contrasenya: EditText
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_registre, container, false)
        return view
    }

    fun loadComponents(view:View) {
        nom = view.findViewById(R.id.nomReg)
        cognom = view.findViewById(R.id.cognomReg)
        contrasenya = view.findViewById(R)
    }



}