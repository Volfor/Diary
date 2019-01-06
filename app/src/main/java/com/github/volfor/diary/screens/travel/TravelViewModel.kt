package com.github.volfor.diary.screens.travel

import androidx.lifecycle.LiveData
import com.github.volfor.diary.base.BaseViewModel
import com.github.volfor.diary.models.Travel
import com.github.volfor.diary.repositories.TravelsRepository

class TravelViewModel(
    private val travelsRepository: TravelsRepository
) : BaseViewModel() {

    lateinit var travel: LiveData<Travel>

    fun init(travelId: String) {
        travel = travelsRepository.loadTravel(travelId)
    }
}