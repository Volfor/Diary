package com.github.volfor.diary.screens.travels.item

import com.github.volfor.diary.models.Travel

class TravelItem(
    private val travel: Travel
) {

    val title: String
        get() = travel.title ?: ""

    val date: String
    get() = "October 10 - 16"

}