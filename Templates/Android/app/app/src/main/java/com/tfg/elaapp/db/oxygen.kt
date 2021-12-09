package com.tfg.elaapp.db
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.io.Serializable

open class oxygen(
    var user: String = "",
    var value : String = "0",
    @PrimaryKey
    var date: String = "",
): Serializable, RealmObject()
