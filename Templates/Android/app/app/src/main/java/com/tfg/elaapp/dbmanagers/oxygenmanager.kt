package com.tfg.elaapp.dbmanagers

import com.tfg.elaapp.db.oxygen
import com.tfg.elaapp.db.pulse
import io.realm.Realm

object oxygenmanager {
    val realm: Realm = Realm.getDefaultInstance()
    fun addoxygen(oxygen: oxygen) {
        realm.executeTransaction {
            it.copyToRealm(oxygen)

        }
    }

    fun getAll(): List<oxygen> {
        return realm.where(
            oxygen::class.java
        ).findAll()

    }

    fun deleteOxygen() {
        val list = getAll()
        for(item in list) {
            realm.executeTransaction {
                item.deleteFromRealm()
            }
        }
    }
}