package com.github.volfor.diary.models

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Travel(
    var title: String? = "",
    var description: String? = "",
    var startTime: Long? = 0,
    var endTime: Long? = 0
)