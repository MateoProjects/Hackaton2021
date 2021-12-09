package com.tfg.elaapp.db

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.io.Serializable

open class sentences (
    var time: String = "",
    var sentence: Int = 0,
    var user: String = "",
    @PrimaryKey
    var date: String = ""
    ): Serializable, RealmObject()