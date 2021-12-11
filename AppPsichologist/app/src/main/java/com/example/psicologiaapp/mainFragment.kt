package com.example.psicologiaapp

import android.app.DownloadManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

import okhttp3.*



class mainFragment : Fragment() {
    private val client = OkHttpClient()
    private val ip = "192.168.1.54"

    lateinit var user: EditText
    lateinit var buttonFind: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        loadComponents(view)
        enableListener(view)
        return view
    }

    fun loadComponents(view:View) {
        user = view.findViewById(R.id.editTextCerca)
        buttonFind = view.findViewById(R.id.cercaButton)
    }

    fun enableListener(view:View) {
        buttonFind.setOnClickListener {
            val requestGet = Request.Builder().get().url("http://$ip:8080/scores").build()

        }
    }


}