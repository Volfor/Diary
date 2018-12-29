package com.github.volfor.diary.screens.travels

import androidx.lifecycle.LifecycleOwner
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.github.volfor.diary.base.BaseViewModel
import com.github.volfor.diary.models.Travel
import com.github.volfor.diary.repositories.TravelsRepository
import com.github.volfor.diary.screens.travels.TravelsActivity.Event
import com.github.volfor.diary.screens.travels.item.TravelItem


class TravelsViewModel(
    private val travelsRepository: TravelsRepository
) : BaseViewModel<TravelsActivity.Event>(), TravelItem.Interactor {
    lateinit var options: FirebaseRecyclerOptions<TravelItem>

    fun init(lifecycleOwner: LifecycleOwner) {
        options = FirebaseRecyclerOptions.Builder<TravelItem>()
            .setQuery(travelsRepository.loadTravels()) { snapshot ->
                TravelItem(this, snapshot.getValue(Travel::class.java)!!)
            }
            .setLifecycleOwner(lifecycleOwner)
            .build()
    }

    override fun onTravelSelected(item: TravelItem) {
        _viewAction.value = Event.Toast("item ${item.title} selected")
    }

    fun createTravel() {
        _viewAction.value = Event.NewTravel
    }
}