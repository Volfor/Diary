package com.github.volfor.diary.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.github.volfor.diary.CoroutineContextHolder
import com.github.volfor.diary.Firebase
import com.github.volfor.diary.base.BaseRepository
import com.github.volfor.diary.extensions.map
import com.github.volfor.diary.livedata.FirebaseQueryLiveData
import com.github.volfor.diary.models.Travel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.withContext
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


class TravelsRepository(
    database: FirebaseDatabase,
    ctx: CoroutineContextHolder
) : BaseRepository(), CoroutineContextHolder by ctx {
    override val ref by lazy {
        database.getReference(Firebase.Travel.REF)
            .child(FirebaseAuth.getInstance().currentUser!!.uid)
    }

    fun loadTravels(): LiveData<List<Travel>> {
        return Transformations.map(FirebaseQueryLiveData(ref.orderByChild(Firebase.Travel.KEY_START))) { snapshot ->
            snapshot.children.map {
                it.getValue(Travel::class.java)!!.apply { id = it.key }
            }.reversed()
        }
    }

    fun loadTravel(id: String) = FirebaseQueryLiveData(ref.child(id)).map {
        it.getValue(Travel::class.java)!!.apply { this.id = it.key }
    }

    suspend fun save(travel: Travel): Boolean = withContext(io) {
        saveTravel(travel)
    }

    private suspend fun saveTravel(travel: Travel): Boolean = suspendCoroutine { cont ->
        val key = ref.push().key
        if (key == null) {
            cont.resume(false)
            return@suspendCoroutine
        }

        ref.child(key)
            .setValue(travel)
            .addOnCompleteListener {
                cont.resume(it.isSuccessful)
            }
    }
}