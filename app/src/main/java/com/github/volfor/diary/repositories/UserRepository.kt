package com.github.volfor.diary.repositories

import com.github.volfor.diary.CoroutineContextHolder
import com.github.volfor.diary.Firebase
import com.github.volfor.diary.base.BaseRepository
import com.github.volfor.diary.models.User
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.withContext
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class UserRepository(
    database: FirebaseDatabase,
    ctx: CoroutineContextHolder
) : BaseRepository(), CoroutineContextHolder by ctx {

    override val ref by lazy { database.getReference(Firebase.User.REF) }

    suspend fun create(firebaseUser: FirebaseUser): Boolean = withContext(io) {
        createUser(firebaseUser)
    }

    private suspend fun createUser(firebaseUser: FirebaseUser): Boolean = suspendCoroutine { cont ->
        val user = User.map(firebaseUser)
        ref.child(user.id!!)
            .setValue(user)
            .addOnCompleteListener {
                cont.resume(it.isSuccessful)
            }
    }
}