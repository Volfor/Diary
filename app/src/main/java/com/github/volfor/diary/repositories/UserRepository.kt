package com.github.volfor.diary.repositories

import com.github.volfor.diary.Firebase
import com.github.volfor.diary.base.BaseRepository
import com.github.volfor.diary.models.User
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import io.reactivex.subjects.PublishSubject

class UserRepository(
    database: FirebaseDatabase
) : BaseRepository() {
    override val ref by lazy { database.getReference(Firebase.User.REF) }

    fun create(firebaseUser: FirebaseUser): PublishSubject<Boolean> {
        val result = PublishSubject.create<Boolean>()

        val user = User.map(firebaseUser)
        ref.child(user.id!!)
            .setValue(user)
            .addOnCompleteListener {
                result.onNext(it.isSuccessful)
            }

        return result
    }
}