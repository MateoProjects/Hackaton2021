package com.tfg.elaapp.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.tfg.elaapp.MainActivity
import com.tfg.elaapp.R
import com.tfg.elaapp.db.eat_time
import com.tfg.elaapp.dbmanagers.eattimemanager
import com.tfg.elaapp.dialogs.timeEat.CorrecAddTimeEat
import com.tfg.elaapp.dialogs.timeEat.IncorrectAddTimeEat
import com.tfg.elaapp.utils.hideSoftKeyboard
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class TimeEat : Fragment() {
    private lateinit var time : EditText
    private lateinit var save : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_time_eat, container, false)
        loadComponents(view)
        setUpListeners(view)
        return view
    }

    /**
     * Load components
     * @param view View
     */
    private fun loadComponents(view:View) {
        time = view.findViewById(R.id.minuts_eat)
        save = view.findViewById(R.id.buttonSaveTimeEat)
    }

    /**
     * SetUp listeners
     * @param view View
     */
    private fun setUpListeners(view:View) {
        save.setOnClickListener {
            hideSoftKeyboard(activity as MainActivity)
            if (time.text.isNotBlank()) {
                val timeDate = LocalDateTime.now()
                val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")
                val formatted = timeDate.format(formatter)
                val sharedPref = this.activity?.getPreferences(Context.MODE_PRIVATE)
                val name = sharedPref?.getString("NAME", "")
                eattimemanager.addTime(eat_time(date=formatted, time=time.text.toString(), user=name))
                CorrecAddTimeEat().newInstance(view.context, requireActivity()).show()

            }

            else {
                IncorrectAddTimeEat().newInstance(view.context, requireActivity()).show()
            }
        }
    }


}