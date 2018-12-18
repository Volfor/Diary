package com.github.volfor.diary.models

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class User(
    @set:Exclude @get:Exclude var id: String? = null,
    var name: String? = "",
    var email: String? = ""
) {

    companion object {
        fun map(firebaseUser: FirebaseUser): User = User(
            firebaseUser.uid,
            firebaseUser.displayName,
            firebaseUser.email
        )
    }
}