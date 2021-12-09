package com.tfg.elaapp.fragments

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.tfg.elaapp.R
import com.tfg.elaapp.dbmanagers.*
import java.io.File
import java.io.FileOutputStream


class SendData : Fragment() {
    private lateinit var cbapat : ImageButton
    private lateinit var cbsentences : ImageButton
    private lateinit var cbmetrics: ImageButton
    private lateinit var metricsText: TextView
    private lateinit var apatsText: TextView
    private lateinit var sentencesText: TextView
    private lateinit var sendData : Button
    private var clickedSentences : Boolean = false
    private var clickedApat : Boolean = false
    private var clickedMetrics : Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_send_data, container, false)
        loadComponents(view)
        setUpListeners(view)
        return view
    }

    private fun loadComponents(view: View) {
        cbapat = view.findViewById(R.id.checkbox_apats)
        cbsentences = view.findViewById(R.id.checkbox_pronunciació)
        cbmetrics = view.findViewById(R.id.checkbox_metrics)
        sendData = view.findViewById(R.id.buttonSendData)
        metricsText = view.findViewById(R.id.metrics_sendData)
        apatsText = view.findViewById(R.id.apats_sendData)
        sentencesText = view.findViewById(R.id.sentences_sendData)
    }

    private fun setUpListeners(view: View) {
        var fileNameEat: String = "temp_eat"
        var fileNameSentences: String = "temp_sentences"
        var fileNameMetrics: String = "temp_metrics"
        val fileOutputStream: FileOutputStream
        val path = view.context?.getExternalFilesDir(null)
        val letDirectory = File(path, "LET")
        letDirectory.mkdirs()
        val sharedPref = this.activity?.getPreferences(Context.MODE_PRIVATE)
        val emailIntent = Intent(Intent.ACTION_SEND)
        val to = sharedPref?.getString("EMAIL", "")
        val name = sharedPref?.getString("NAME", "")
        val lastname = sharedPref?.getString("LASTNAME", "")
        val addressees = arrayOf(to)
        var datatest = ""
        emailIntent.data = Uri.parse("mailto:")
        emailIntent.type = "text/plain"
        emailIntent.putExtra(Intent.EXTRA_EMAIL, addressees)
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Enviament automatitzat de les dades del "
                + "pacient: " + name + " " + lastname)
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Informe de seguiment del pacient: " +
                name + " " + lastname)
        sentencesText.setOnClickListener{
            if (clickedSentences) {
                clickedSentences = !clickedSentences
                cbsentences.setImageResource(R.drawable.checkbox_pulsed)
            }
            else {
                clickedSentences = !clickedSentences
                cbsentences.setImageResource(R.drawable.empty_check_box)
            }
        }
        apatsText.setOnClickListener{
            if (clickedApat == false) {
                clickedApat = !clickedApat
                cbapat.setImageResource(R.drawable.checkbox_pulsed)
            }
            else {
                clickedApat = true
                cbapat.setImageResource(R.drawable.empty_check_box)
            }
        }
        metricsText.setOnClickListener{
            if (clickedMetrics) {
                clickedMetrics = !clickedMetrics
                cbmetrics.setImageResource(R.drawable.checkbox_pulsed)
            }
            else {
                clickedMetrics = !clickedMetrics
                cbmetrics.setImageResource(R.drawable.empty_check_box)
            }
        }

        sendData.setOnClickListener {
            if(clickedApat == true) {
                val file = File(letDirectory, "apats.txt")
                val data = eattimemanager.getAll()

                datatest = datatest + "---- Inici Apats ---- \n"
                for(i in data) {
                    datatest = datatest + (i.date + ";" + i.time + ";\n")
                }
                datatest = datatest + "---- Fi Apats ---- \n"
                val uri = Uri.fromFile(file)



            }
            if (clickedMetrics) {
                val data = metricsmanager.getAll()
                datatest = datatest + "---- Inici metrics ---- \n"
                datatest = datatest + "ox,hr\n"
                for(i in data) {
                    datatest = datatest + (i.valueox + "," + i.valuehr + "\n")
                }
                datatest = datatest + "---- Fi metrics ---- \n"

            }

            if( clickedSentences) {
                val data = sentencemanager.getAll()
                datatest = datatest + "---- Inici frases ---- \n"
                for(i in data) {
                    datatest = datatest + (i.date + ";" + i.sentence.toString() + i.time + "\n")
                }
                datatest = datatest + "---- Fi frases ---- \n"


            }




            emailIntent.putExtra(Intent.EXTRA_TEXT, datatest)
            startActivity(Intent.createChooser(emailIntent, "Pick an Email provider"))

        }
        cbapat.setOnClickListener {
            if (clickedApat == false) {
                clickedApat = true
                cbapat.setImageResource(R.drawable.checkbox_pulsed)
            }
            else {
                clickedApat = false
                cbapat.setImageResource(R.drawable.empty_check_box)
            }
        }

        cbsentences.setOnClickListener {
            if (clickedSentences == false) {
                clickedSentences = true
                cbsentences.setImageResource(R.drawable.checkbox_pulsed)
            }
            else {
                clickedSentences = false
                cbsentences.setImageResource(R.drawable.empty_check_box)
            }
        }

        cbmetrics.setOnClickListener{
            if (clickedMetrics == false) {
                clickedMetrics = true
                cbmetrics.setImageResource(R.drawable.checkbox_pulsed)
            }
            else {
                clickedMetrics = false
                cbmetrics.setImageResource(R.drawable.empty_check_box)
            }
        }
    }


}