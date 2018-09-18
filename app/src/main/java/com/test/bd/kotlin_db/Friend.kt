package com.test.bd.kotlin_db

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Friend : RealmObject() {
    @SerializedName("id")
    @Expose
    @PrimaryKey
    var id: Int? = null
    @SerializedName("name")
    @Expose
    var name: String? = null

}
