package com.github.volfor.diary.screens.travel.create

import androidx.lifecycle.MutableLiveData
import com.github.volfor.diary.BR
import com.github.volfor.diary.CoroutineContextHolder
import com.github.volfor.diary.R
import com.github.volfor.diary.base.BaseEventViewModel
import com.github.volfor.diary.models.Destination
import com.github.volfor.diary.models.Travel
import com.github.volfor.diary.repositories.TravelsRepository
import com.github.volfor.diary.screens.travel.create.TravelCreateFragment.Event
import com.github.volfor.diary.toolbar.ToolbarData
import com.github.volfor.diary.toolbar.ToolbarItem
import kotlinx.coroutines.launch
import me.tatarka.bindingcollectionadapter2.ItemBinding
import java.util.*

class TravelCreateViewModel(
    ctx: CoroutineContextHolder,
    private val travelsRepository: TravelsRepository
) : BaseEventViewModel<TravelCreateFragment.Event>(ctx), DestinationItem.Listener {

    val toolbarData = ToolbarData(
        title = "Create Travel",
        menuResId = R.menu.create_travel_menu,
        items = listOf(
            ToolbarItem(
                itemId = R.id.actionDone,
                onClick = { done() })
        )
    )

    val destinations = MutableLiveData<List<DestinationItem>>().apply {
        value = mutableListOf(DestinationItem(Calendar.getInstance(), false))
    }

    var itemBinding = ItemBinding.of<DestinationItem>(BR.item, R.layout.item_destination)
        .bindExtra(BR.listener, this@TravelCreateViewModel)

    val title = MutableLiveData<String>()
    val description = MutableLiveData<String>()

    override fun pickStart(item: DestinationItem) {
        sendEvent(Event.StartDate(item))
    }

    override fun pickEnd(item: DestinationItem) {
        sendEvent(Event.EndDate(item))
    }

    override fun remove(item: DestinationItem) {
        val result = destinations.value!!.toMutableList()
        result.remove(item)

        result.first().removeEnabled.value = result.size > 1

        destinations.value = result
    }

    fun add() {
        val result = destinations.value!!.toMutableList()
        result.add(DestinationItem(result.last().end.value!!))

        result.first().removeEnabled.value = result.size > 1

        destinations.value = result
    }

    fun showMessage(message: String) {
        sendEvent(Event.Toast(message))
    }

    fun done() {
        if (title.value.isNullOrBlank()) {
            showMessage("Title is required")
            return
        }

        val destinations = destinations.value?.map {
            Destination(it.location.value!!, it.start.value!!.timeInMillis, it.end.value!!.timeInMillis)
        } ?: emptyList()

        val minDate = destinations.map { it.start }.min() ?: 0
        val maxDate = destinations.map { it.end }.max() ?: 0

        val travel = Travel(
            title.value,
            description.value,
            minDate,
            maxDate
        )

        launch {
            if (travelsRepository.save(travel, destinations)) {
                sendEvent(Event.Done)
            } else {
                showMessage("Error")
            }
        }
    }
}