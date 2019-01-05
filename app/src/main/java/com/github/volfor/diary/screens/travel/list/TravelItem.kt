package com.github.volfor.diary.screens.travel.list

import com.github.volfor.diary.models.Travel

class TravelItem(
    private val interactor: Interactor,
    private val travel: Travel
) {
    interface Interactor {
        fun onTravelSelected(item: TravelItem)
    }

    val title: String
        get() = travel.title ?: ""

    val date: String
        get() = "October 10 - 16"

    fun onClick() {
        interactor.onTravelSelected(this)
    }
}