package com.github.volfor.diary.models

import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Travel(
    var title: String? = "",
    var description: String? = "",
    var start: Long = 0,
    var end: Long = 0,
    @set:Exclude @get:Exclude var id: String? = null
)