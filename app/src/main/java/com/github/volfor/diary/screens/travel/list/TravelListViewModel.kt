package com.github.volfor.diary.screens.travel.list

import androidx.lifecycle.LifecycleOwner
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.github.volfor.diary.base.BaseEventViewModel
import com.github.volfor.diary.models.Travel
import com.github.volfor.diary.repositories.TravelsRepository
import com.github.volfor.diary.screens.travel.list.TravelListFragment.Event


class TravelListViewModel(
    private val travelsRepository: TravelsRepository
) : BaseEventViewModel<TravelListFragment.Event>(), TravelItem.Interactor {
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
        sendEvent(Event.Toast("item ${item.title} selected"))
    }

    fun createTravel() {
        sendEvent(Event.NewTravel)
    }
}