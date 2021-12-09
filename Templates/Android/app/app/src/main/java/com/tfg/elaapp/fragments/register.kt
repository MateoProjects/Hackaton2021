package com.tfg.elaapp.fragments


import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText
import com.tfg.elaapp.R
import com.tfg.elaapp.dialogs.register.CorrectRegister
import com.tfg.elaapp.dialogs.register.ErrorRegister
import com.tfg.elaapp.dialogs.register.InvalidMail
import com.tfg.elaapp.utils.hideSoftKeyboard
import com.tfg.elaapp.utils.isEmailValid


class register : Fragment() {
    private lateinit var buttonRegister: View
    private lateinit var nameRegister: View
    private lateinit var cognomRegister: View
    private lateinit var mailRegister: View
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_register, container, false)
        loadComponents(view)
        setUpListener(view)
        return view
    }

    private fun loadComponents(view: View) {
        buttonRegister = view.findViewById(R.id.save_register_button)
        nameRegister = view.findViewById(R.id.name_register)
        cognomRegister = view.findViewById(R.id.cognom_register)
        mailRegister = view.findViewById(R.id.mail_doctor_register)
    }

    @SuppressLint("CommitPrefEdits")
    private fun setUpListener(view: View) {
        buttonRegister.setOnClickListener{
            hideSoftKeyboard(requireActivity())
            val lastname = cognomRegister.findViewById<TextInputEditText>(R.id.cognom_register).text
            val name = nameRegister.findViewById<TextInputEditText>(R.id.name_register).text
            val doctor = mailRegister.findViewById<TextInputEditText>(R.id.mail_doctor_register).text
            if(doctor.isNullOrBlank() or name.isNullOrBlank() or lastname.isNullOrBlank()) {
                ErrorRegister().newInstance(view.context, requireActivity()).show()
            }
            else if( !doctor.toString().isEmailValid()) {
                InvalidMail().newInstance(view.context, activity!!).show()
            }
            else {
                val sharedPref = this.activity?.getPreferences(Context.MODE_PRIVATE)
                sharedPref?.edit()?.putString("NAME", name.toString())?.apply()
                sharedPref?.edit()?.putString("LASTNAME", lastname.toString())?.apply()
                sharedPref?.edit()?.putString("EMAIL", doctor.toString())?.apply()
                CorrectRegister().newInstance(view.context, activity!!).show()
            }
        }
    }






}