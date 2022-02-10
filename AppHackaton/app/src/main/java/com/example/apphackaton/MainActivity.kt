package com.example.apphackaton

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        callReg()
    }

    fun callReg() {
        supportFragmentManager.beginTransaction().replace(R.id.mainActivity, register()).commit()
    }
}