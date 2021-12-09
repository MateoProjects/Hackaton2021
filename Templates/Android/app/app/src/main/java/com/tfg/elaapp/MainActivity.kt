package com.tfg.elaapp

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.tfg.elaapp.fragments.*


class MainActivity : AppCompatActivity() {

    private lateinit var toggle:ActionBarDrawerToggle
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView : NavigationView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        drawerLayout = this.findViewById(R.id.drawerLayout)
        toggle = ActionBarDrawerToggle(this,drawerLayout, R.string.open, R.string.close )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        mainFrag()
        val sharedPref = this.getPreferences(Context.MODE_PRIVATE)
        val name = sharedPref!!.getString("NAME" , "")
        if(name.toString().isNotEmpty()) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            mainFrag()
        }
        else {
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
            callregister()
        }
        navView = this.findViewById(R.id.navview)
        navView.setNavigationItemSelectedListener {
            drawerLayout.closeDrawer(GravityCompat.START)
            when(it.itemId) {
                R.id.personal_information_menu->{
                    callpersonalinformation()
                }
                R.id.logout-> {
                    supportActionBar?.setDisplayHomeAsUpEnabled(false)
                    callregister()
                }
                R.id.mainFragmentMenu -> {
                    mainFrag()
                }
                R.id.about_menu -> {
                    callAbout()
                }
            }

            true
        }


    }

    /**
     *
     */

    fun mainFrag() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportFragmentManager.beginTransaction().replace(R.id.MainActivity, mainFragment()).commit()
    }

     fun callregister() {
        supportFragmentManager.beginTransaction().replace(R.id.MainActivity,register()).commit()
    }

    fun callpersonalinformation() {
        supportFragmentManager.beginTransaction().replace(R.id.MainActivity, PersonalInformation()).commit()
    }
    fun callEat() {
        supportFragmentManager.beginTransaction().replace(R.id.MainActivity, TimeEat()).commit()
    }

    fun callSentences() {
        supportFragmentManager.beginTransaction().replace(R.id.MainActivity, Sentences()).commit()
    }

    fun callSendData() {
        supportFragmentManager.beginTransaction().replace(R.id.MainActivity, SendData()).commit()
    }
    fun callAbout() {
        supportFragmentManager.beginTransaction().replace(R.id.MainActivity, about()).commit()
    }

    fun callrtmetrics() {
        supportFragmentManager.beginTransaction().replace(R.id.MainActivity, rtmetrics()).commit()
    }


    /**
     * Function to override MenuItem
     * @param item MenuItem MenuItem that we need to override
     * @return Boolean
     */

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {

                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START)
                } else {
                    drawerLayout.openDrawer(GravityCompat.START)
                }

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }




}