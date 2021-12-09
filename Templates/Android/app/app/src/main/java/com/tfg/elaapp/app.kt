package com.tfg.elaapp

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        var config = RealmConfiguration.Builder().name("dataDB.realm")
            .deleteRealmIfMigrationNeeded()
            .build()
        Realm.setDefaultConfiguration(config)

    }
}