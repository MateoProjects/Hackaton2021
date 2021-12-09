package com.tfg.elaapp.db

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.io.Serializable

open class metrics(
    var user: String = "",
    var valueox : String = "0",
    var valuehr : String = "0",
    @PrimaryKey
    var date: String = "",
): Serializable, RealmObject()