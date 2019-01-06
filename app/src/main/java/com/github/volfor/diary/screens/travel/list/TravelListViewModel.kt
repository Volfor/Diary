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
                val travel = snapshot.getValue(Travel::class.java)!!
                travel.id = snapshot.key

                TravelItem(this, travel)
            }
            .setLifecycleOwner(lifecycleOwner)
            .build()
    }

    override fun onTravelSelected(item: TravelItem) {
        item.id?.let { sendEvent(Event.Open(it)) }
    }

    fun createTravel() {
        sendEvent(Event.New)
    }
}