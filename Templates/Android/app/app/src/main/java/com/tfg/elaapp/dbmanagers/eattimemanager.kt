package com.tfg.elaapp.dbmanagers

import com.tfg.elaapp.db.eat_time
import com.tfg.elaapp.db.sentences
import io.realm.Realm

object eattimemanager {
    val realm: Realm = Realm.getDefaultInstance()
    fun addTime(eat: eat_time) {
        realm.executeTransaction {
            it.copyToRealm(eat)
        }
    }

    fun getAll(): List<eat_time> {
        return realm.where(
            eat_time::class.java
        ).findAll()

    }

    fun deleteeatTimes() {
        val list = getAll()
        for(item in list) {
            realm.executeTransaction {
                item.deleteFromRealm()
            }
        }
    }
}