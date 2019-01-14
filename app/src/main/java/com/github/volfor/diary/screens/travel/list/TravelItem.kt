package com.github.volfor.diary.screens.travel.list

import com.github.volfor.diary.models.Travel

data class TravelItem(
    val travel: Travel
) {
    interface Listener {
        fun onClick(item: TravelItem)
    }

    val title: String
        get() = travel.title ?: ""

    val date: String
        get() = "October 10 - 16"
}