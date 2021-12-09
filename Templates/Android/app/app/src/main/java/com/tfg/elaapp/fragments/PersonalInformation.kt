package com.tfg.elaapp.fragments

import android.annotation.SuppressLint
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
import com.tfg.elaapp.dialogs.pi.ModifyErrorPi
import com.tfg.elaapp.utils.*



class PersonalInformation : Fragment() {
    private lateinit var name : EditText
    private lateinit var cog : EditText
    private lateinit var email : EditText
    private lateinit var butBack : Button
    private lateinit var butMod : Button
    private var editable : Boolean = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_personal_information, container, false)
        loadComponents(view)
        setUpListeners(view)
        return view
    }

    private fun loadComponents(view: View) {
        val sharedPref = this.activity?.getPreferences(Context.MODE_PRIVATE)
        name = view.findViewById(R.id.pi_firstname)
        cog = view.findViewById(R.id.pi_lastname)
        email = view.findViewById(R.id.pi_email)
        butBack = view.findViewById(R.id.personal_information_back)
        butMod = view.findViewById(R.id.save_edit_personal_information)
        name.setText(sharedPref?.getString("NAME", ""))
        cog.setText(sharedPref?.getString("LASTNAME", ""))
        email.setText(sharedPref?.getString("EMAIL", ""))
    }
    @SuppressLint("CommitPrefEdits", "ResourceAsColor", "SetTextI18n")
    private fun setUpListeners(view: View) {
        val sharedPref = this.activity?.getPreferences(Context.MODE_PRIVATE)
        butBack.setOnClickListener {
            (activity as MainActivity).mainFrag()
        }

        butMod.setOnClickListener {
            if(editable) {
                editable = !editable
                if (email.text.toString().isEmailValid() and name.text.isNotEmpty()
                        and cog.text.isNotEmpty()) {
                    butMod.setBackgroundColor(R.color.green_1)
                    name.isFocusableInTouchMode = false
                    cog.isFocusableInTouchMode = false
                    email.isFocusableInTouchMode = false
                    sharedPref!!.edit().putString("NAME", name.text.toString()).apply()
                    sharedPref.edit().putString("LASTNAME", cog.text.toString()).apply()
                    sharedPref.edit().putString("EMAIL", email.text.toString()).apply()
                    hideSoftKeyboard(activity as MainActivity)
                    butMod.setText(R.string.edita_info_p)
                    name.isCursorVisible = false
                    cog.isCursorVisible = false
                    email.isCursorVisible = false
                }

                else {
                    hideSoftKeyboard(activity as MainActivity)
                    ModifyErrorPi().newInstance(view.context, activity!!).show()
                }

            }
            else {
                name.isFocusableInTouchMode = true
                cog.isFocusableInTouchMode = true
                email.isFocusableInTouchMode = true
                name.isCursorVisible = true
                cog.isCursorVisible = true
                email.isCursorVisible = true
                butMod.text = "Guardar"
                butMod.setBackgroundColor(R.color.red_1)
                editable = !editable
            }

        }
    }

}