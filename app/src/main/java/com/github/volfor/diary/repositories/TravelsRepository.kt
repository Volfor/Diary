package com.github.volfor.diary.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.github.ajalt.timberkt.Timber.w
import com.github.volfor.diary.CoroutineContextHolder
import com.github.volfor.diary.Firebase
import com.github.volfor.diary.base.BaseRepository
import com.github.volfor.diary.extensions.await
import com.github.volfor.diary.extensions.map
import com.github.volfor.diary.livedata.FirebaseQueryLiveData
import com.github.volfor.diary.models.Destination
import com.github.volfor.diary.models.Travel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.withContext


class TravelsRepository(
    database: FirebaseDatabase,
    ctx: CoroutineContextHolder
) : BaseRepository(), CoroutineContextHolder by ctx {
    override val ref by lazy {
        database.getReference(Firebase.Travel.REF)
            .child(FirebaseAuth.getInstance().currentUser!!.uid)
    }

    private val destinationsRef by lazy { database.getReference(Firebase.Destination.REF) }

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

    suspend fun save(travel: Travel, destinations: List<Destination>): Boolean = withContext(io) {
        val travelId = saveTravel(travel)

        return@withContext if (travelId != null) {
            saveDestinations(travelId, destinations)
            true
        } else false
    }

    private suspend fun saveTravel(travel: Travel): String? {
        val key = ref.push().key ?: return null

        return try {
            ref.child(key).setValue(travel).await()
            key
        } catch (e: Exception) {
            w { "Failed to store travel: $travel. Reason: $e" }
            null
        }
    }

    private suspend fun saveDestinations(travelId: String, destinations: List<Destination>) {
        val ref = destinationsRef.child(travelId)

        destinations.forEach { destination ->
            val key = ref.push().key

            try {
                ref.child(key!!).setValue(destination).await()
            } catch (e: Exception) {
                w { "Failed to store destination: $destination. Reason: $e" }
            }
        }
    }
}