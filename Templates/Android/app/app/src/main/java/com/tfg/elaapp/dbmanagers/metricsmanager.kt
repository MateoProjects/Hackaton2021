package com.tfg.elaapp.dbmanagers
import com.tfg.elaapp.db.metrics
import io.realm.Realm


object metricsmanager {


        val realm: Realm = Realm.getDefaultInstance()
        fun addmetrics(metrics: metrics) {
            realm.executeTransaction {
                it.copyToRealm(metrics)

            }
        }

        fun getAll(): List<metrics> {
            return realm.where(
                metrics::class.java
            ).findAll()

        }

        fun deletemetrics() {
            val list = getAll()
            for(item in list) {
                realm.executeTransaction {
                    item.deleteFromRealm()
                }
            }
        }
}
