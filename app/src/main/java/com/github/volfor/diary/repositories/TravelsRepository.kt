package com.github.volfor.diary.repositories

import com.github.volfor.diary.TRAVELS_REF
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase


class TravelsRepository(
    database: FirebaseDatabase
) {
    private val travelsRef by lazy {
        database.getReference(TRAVELS_REF)
            .child(FirebaseAuth.getInstance().currentUser!!.uid)
    }

    fun loadTravels() = travelsRef.orderByValue()
}