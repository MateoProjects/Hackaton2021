package com.tfg.elaapp.db

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.io.Serializable

open class eat_time (
    var time: String = "",
    var user: String? = "",
    @PrimaryKey
    var date: String = ""

) : Serializable, RealmObject()