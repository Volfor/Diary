package com.github.volfor.diary.repositories

import com.github.volfor.diary.USERS_REF
import com.github.volfor.diary.models.User
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import io.reactivex.subjects.PublishSubject

class UserRepository(
    database: FirebaseDatabase
) {
    private val usersRef by lazy { database.getReference(USERS_REF) }

    fun create(firebaseUser: FirebaseUser): PublishSubject<Boolean> {
        val result = PublishSubject.create<Boolean>()

        val user = User.map(firebaseUser)
        usersRef.child(user.id!!)
            .setValue(user)
            .addOnCompleteListener {
                result.onNext(it.isSuccessful)
            }

        return result
    }
}