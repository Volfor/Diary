package com.github.volfor.diary.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.github.volfor.diary.TRAVELS_REF
import com.github.volfor.diary.base.BaseRepository
import com.github.volfor.diary.extensions.map
import com.github.volfor.diary.livedata.FirebaseQueryLiveData
import com.github.volfor.diary.models.Travel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import io.reactivex.subjects.PublishSubject


class TravelsRepository(
    database: FirebaseDatabase
) : BaseRepository() {
    override val ref by lazy {
        database.getReference(TRAVELS_REF)
            .child(FirebaseAuth.getInstance().currentUser!!.uid)
    }

    fun loadTravels() = ref.orderByValue()

    fun loadTravel(id: String) = FirebaseQueryLiveData(ref.child(id)).map {
        it.getValue(Travel::class.java)!!
    }

    fun saveTravel(travel: Travel): PublishSubject<Boolean> {
        val result = PublishSubject.create<Boolean>()

        val key = ref.push().key
        if (key == null) {
            result.onNext(false)
            return result
        }

        ref.child(key)
            .setValue(travel)
            .addOnCompleteListener {
                result.onNext(it.isSuccessful)
            }

        return result
    }
}