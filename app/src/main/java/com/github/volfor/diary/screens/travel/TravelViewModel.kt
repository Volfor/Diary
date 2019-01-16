package com.github.volfor.diary.screens.travel

import androidx.lifecycle.LiveData
import com.github.volfor.diary.CoroutineContextHolder
import com.github.volfor.diary.base.BaseViewModel
import com.github.volfor.diary.models.Travel
import com.github.volfor.diary.repositories.TravelsRepository

class TravelViewModel(
    ctx: CoroutineContextHolder,
    private val travelsRepository: TravelsRepository
) : BaseViewModel(ctx) {

    lateinit var travel: LiveData<Travel>

    fun init(travelId: String) {
        travel = travelsRepository.loadTravel(travelId)
    }
}