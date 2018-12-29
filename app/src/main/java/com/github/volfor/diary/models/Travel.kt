package com.github.volfor.diary.models

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Travel(
    var title: String? = "",
    var description: String? = ""
)