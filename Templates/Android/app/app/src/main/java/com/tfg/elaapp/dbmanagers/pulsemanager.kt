package com.tfg.elaapp.dbmanagers

import com.tfg.elaapp.db.pulse
import com.tfg.elaapp.db.sentences
import io.realm.Realm

object pulsemanager {
    val realm: Realm = Realm.getDefaultInstance()
    fun addPulse(pulse: pulse) {
        realm.executeTransaction {
            it.copyToRealm(pulse)
        }
    }

    fun getAll(): List<pulse> {
        return realm.where(
            pulse::class.java
        ).findAll()

    }

    fun deletepulses() {
        val list = getAll()
        for(item in list) {
            realm.executeTransaction {
                item.deleteFromRealm()
            }
        }
    }
}