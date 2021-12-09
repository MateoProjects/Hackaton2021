package com.tfg.elaapp.dbmanagers

import com.tfg.elaapp.db.sentences
import io.realm.Realm

object sentencemanager {
    val realm: Realm = Realm.getDefaultInstance()
    fun addSentence(sentence: sentences) {
        realm.executeTransaction {
            it.copyToRealm(sentence)
        }
    }

    fun getAll(): List<sentences> {
        return realm.where(
            sentences::class.java
        ).findAll()

    }

    fun deleteSentences() {
        val list = getAll()
        for(item in list) {
            realm.executeTransaction {
                item.deleteFromRealm()
            }
        }
    }
}