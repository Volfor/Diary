package com.github.volfor.diary.base

import com.google.firebase.database.DatabaseReference

abstract class BaseRepository {
    protected abstract val ref: DatabaseReference
}