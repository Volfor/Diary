package com.github.volfor.diary.screens.travels

import androidx.lifecycle.LifecycleOwner
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.github.volfor.diary.base.BaseViewModel
import com.github.volfor.diary.livedata.ViewAction
import com.github.volfor.diary.models.Travel
import com.github.volfor.diary.repositories.TravelsRepository


class TravelsViewModel(
    private val travelsRepository: TravelsRepository
) : BaseViewModel<ViewAction>() {
    lateinit var options: FirebaseRecyclerOptions<Travel>

    fun init(lifecycleOwner: LifecycleOwner) {
        options = FirebaseRecyclerOptions.Builder<Travel>()
            .setQuery(travelsRepository.loadTravels(), Travel::class.java)
            .setLifecycleOwner(lifecycleOwner)
            .build()
    }
}