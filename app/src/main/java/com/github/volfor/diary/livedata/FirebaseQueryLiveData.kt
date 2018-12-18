package com.github.volfor.diary.livedata

import android.os.Handler
import androidx.lifecycle.LiveData
import com.github.ajalt.timberkt.e
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener

class FirebaseQueryLiveData(
    val query: Query
) : LiveData<DataSnapshot>() {

    private var listenerRemovePending = false

    private val handler by lazy { Handler() }
    private val removeListener by lazy {
        Runnable {
            query.removeEventListener(listener)
            listenerRemovePending = false
        }
    }

    private val listener by lazy { CustomValueEventListener() }

    override fun onActive() {
        if (listenerRemovePending) {
            handler.removeCallbacks(removeListener)
        } else {
            query.addValueEventListener(listener)
        }
        listenerRemovePending = false
    }

    override fun onInactive() {
        // Listener removal is schedule on two second delay
        handler.postDelayed(removeListener, 2000)
        listenerRemovePending = true
    }

    inner class CustomValueEventListener : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            value = snapshot
        }

        override fun onCancelled(error: DatabaseError) {
            e(error.toException()) { "Can't listen to query $query" }
        }
    }
}