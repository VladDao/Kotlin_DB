package com.test.bd.kotlin_db

import android.net.Uri
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

//extends RealmObject
@RealmClass
open class ContactList : RealmObject() {
    @PrimaryKey
    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("isActive")
    @Expose
    var isActive: Boolean? = null
    @SerializedName("balance")
    @Expose
    var balance: String? = null
    @SerializedName("picture")
    @Expose
    private var picture: String? = null
    @SerializedName("age")
    @Expose
    var age: Int? = null
    @SerializedName("eyeColor")
    @Expose
    var eyeColor: String? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("gender")
    @Expose
    var gender: String? = null
    @SerializedName("company")
    @Expose
    var company: String? = null
    @SerializedName("email")
    @Expose
    var email: String? = null
    @SerializedName("phone")
    @Expose
    var phone: String? = null
    @SerializedName("address")
    @Expose
    var address: String? = null
    @SerializedName("about")
    @Expose
    var about: String? = null
    @SerializedName("registered")
    @Expose
    var registered: String? = null
    @SerializedName("tags")
    @Expose
    var tags: RealmList<String>? = null
    @SerializedName("friends")
    @Expose
    var friends: RealmList<Friend>? = null
    @SerializedName("favoriteFruit")
    @Expose
    var favoriteFruit: String? = null

  /*  fun getId(): Long {
        return java.lang.Long.parseLong(id, 16)
    }

    fun setId(id: Long?) {
        //id = Long.toHexString(id);
        this.id = java.lang.Long.toHexString(id!!)
    }
*/
    fun getPicture(): Uri {
        return Uri.parse(picture)
    }

    fun setPicture(picture: String) {
        this.picture = picture
    }

}




