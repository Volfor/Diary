package com.github.volfor.diary.repositories

import com.github.volfor.diary.TRAVELS_REF
import com.github.volfor.diary.models.Travel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import io.reactivex.subjects.PublishSubject


class TravelsRepository(
    database: FirebaseDatabase
) {
    private val travelsRef by lazy {
        database.getReference(TRAVELS_REF)
            .child(FirebaseAuth.getInstance().currentUser!!.uid)
    }

    fun loadTravels() = travelsRef.orderByValue()

    fun saveTravel(travel: Travel): PublishSubject<Boolean> {
        val result = PublishSubject.create<Boolean>()

        val key = travelsRef.push().key
        if (key == null) {
            result.onNext(false)
            return result
        }

        travelsRef.child(key)
            .setValue(travel)
            .addOnCompleteListener {
                result.onNext(it.isSuccessful)
            }

        return result
    }
}