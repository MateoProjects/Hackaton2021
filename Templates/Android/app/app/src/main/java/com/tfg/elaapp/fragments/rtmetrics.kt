package com.tfg.elaapp.fragments

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.tfg.elaapp.MainActivity
import com.tfg.elaapp.R
import com.tfg.elaapp.db.metrics
import com.tfg.elaapp.dbmanagers.metricsmanager
import com.tfg.elaapp.dialogs.bluetooth.BluetoothNotSuported
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.IOException
import java.util.*



class rtmetrics() : Fragment() {
    private var myUUID: UUID? = null
    private lateinit var oxygen : TextView
    private lateinit var hrate : TextView
    private lateinit var buttonStart: Button
    private lateinit var buttonBack : Button
    private val bluetoothAdapter: BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter()
    private var bluetoothDevice: BluetoothDevice? = null
    private var bluetoothSocket: BluetoothSocket? = null
    private var metric : Boolean = false
    private var out : Boolean = false
    private var clicked : Boolean = false
    private val mmBuffer: ByteArray = ByteArray(1024) // mmBuffer store for the stream
    private lateinit var name : String



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_rtmetrics, container, false)

        loadComponents(view)
        setUpListeners()
        startBluetooth(view)
        connect()
        return view
    }

    private fun loadComponents(view:View) {
        buttonBack = view.findViewById(R.id.buttonBackMetrics)
        buttonStart = view.findViewById(R.id.buttonStartMetrics)
        oxygen = view.findViewById(R.id.oxygenNumber)
        hrate = view.findViewById(R.id.hratenumber)
        myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")
        val sharedPref = this.activity?.getPreferences(Context.MODE_PRIVATE)
        name = sharedPref!!.getString("NAME" , "").toString()
    }

    private fun setUpListeners() {
        buttonBack.setOnClickListener {

            if(bluetoothSocket !=  null) {
                bluetoothSocket?.close()

            }
            metric = false
            while(out == false) {}
            (activity as MainActivity).mainFrag()

        }

        buttonStart.setOnClickListener {

            if(clicked == false){
                clicked = true
                out = false
                buttonStart.setText(R.string.stop)
                metric = true
                GlobalScope.launch {
                    metrics()
                }
            }
            else {
                clicked = false
                buttonStart.setText(R.string.start)
                metric = false
            }

        }
    }

    private fun metrics() {
        var bytes: Int
        val outputStream = bluetoothSocket?.outputStream
        val inputStream = bluetoothSocket?.inputStream
        val start:String = "r"
        outputStream?.write(start.toByteArray()) // enviem algo per inicialitzar
        while(metric){
            bytes = inputStream!!.read(mmBuffer)
            var conversion = String(mmBuffer, 0, bytes)
            while(!conversion.contains(';')) {
                bytes = inputStream!!.read(mmBuffer)
                conversion += String(mmBuffer, 0, bytes)
            }
            val coma = conversion.indexOf(',')
            val close = conversion.indexOf(';')
            val ox = conversion.substring(0,coma)
            val hr = conversion.substring(coma+1, close)
            oxygen.setText(ox)
            hrate.setText(hr)
            outputStream?.write(start.toByteArray())
            metricsmanager.addmetrics(metrics(user=name, valueox=ox,
                valuehr=hr, Date().time.toString()))
        }
        out = true

    }



    /* A partir d'aqui tot és Bluetooth */

    private fun startBluetooth(view: View) {
        if (bluetoothAdapter == null) {
            BluetoothNotSuported().newInstance(view.context, requireActivity()).show()
        }

        if (bluetoothAdapter?.isEnabled == false) {
            Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
        }

        val pairedDevices: Set<BluetoothDevice>? = bluetoothAdapter?.bondedDevices
        if (pairedDevices?.size!! > 0) {
            for(d in pairedDevices) {
                bluetoothDevice = d
            }
            if(bluetoothDevice == null) {
                ///Toast.makeText(context, "Dispositiu no trobat", Toast.LENGTH_LONG).show()
            }
            else {
                // Toast.makeText(context, "Dispositiu trobat", Toast.LENGTH_LONG).show()
            }
        }

    }



    fun Connect(): Boolean {

        val device = bluetoothDevice
        Log.d("", "Connecting to ... $device")
        Toast.makeText(context, "Connecting to ... ${device?.name} mac: ${device!!.uuids[0]} address: ${device.address}", Toast.LENGTH_LONG).show()
        bluetoothAdapter?.cancelDiscovery()
        try {
            bluetoothSocket = device?.createRfcommSocketToServiceRecord(myUUID)
            /* Here is the part the connection is made, by asking the device to create a RfcommSocket (Unsecure socket I guess), It map a port for us or something like that */
            bluetoothSocket?.connect()
            Log.d("", "Connection made.")
            Toast.makeText(requireContext(), "Connection made.", Toast.LENGTH_SHORT).show()
            return true
        } catch (e: IOException) {
            try {
                bluetoothSocket?.close()
            } catch (e2: IOException) {
                Log.d("", "Unable to end the connection")
                Toast.makeText(requireContext(), "Unable to end the connection", Toast.LENGTH_SHORT).show()
                return false
            }

            Log.d("", "Socket creation failed")
            Toast.makeText(requireContext(), "Socket creation failed", Toast.LENGTH_SHORT).show()
        }
        return false
        }

    fun connect() :Boolean {
        val device = bluetoothDevice
        Toast.makeText(context, "Connecting to ... ${device?.name}", Toast.LENGTH_LONG).show()
        try {
            bluetoothSocket = device?.createRfcommSocketToServiceRecord(myUUID)
            /* Here is the part the connection is made, by asking the device to create a RfcommSocket (Unsecure socket I guess), It map a port for us or something like that */
            bluetoothSocket?.connect()
            Toast.makeText(context, "Connection made.", Toast.LENGTH_LONG).show()
            return true
        } catch (e: IOException) {
            try {
                bluetoothSocket?.close()
                return false
            } catch (e2: IOException) {
            }
            Toast.makeText(context, "Socket creation failed", Toast.LENGTH_LONG).show()
        }
        return false
    }





}

