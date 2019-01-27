package com.github.volfor.diary.screens.travel.create

import androidx.lifecycle.MutableLiveData
import com.github.volfor.diary.BR
import com.github.volfor.diary.CoroutineContextHolder
import com.github.volfor.diary.R
import com.github.volfor.diary.base.BaseEventViewModel
import com.github.volfor.diary.repositories.TravelsRepository
import com.github.volfor.diary.screens.travel.create.TravelCreateFragment.Event
import me.tatarka.bindingcollectionadapter2.ItemBinding
import java.util.*


class TravelCreateViewModel(
    ctx: CoroutineContextHolder,
    private val travelsRepository: TravelsRepository
) : BaseEventViewModel<TravelCreateFragment.Event>(ctx), DestinationItem.Listener {

    val items = MutableLiveData<List<DestinationItem>>().apply {
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
        val result = items.value!!.toMutableList()
        result.remove(item)

        result.first().removeEnabled.value = result.size > 1

        items.value = result
    }

    fun add() {
        val result = items.value!!.toMutableList()
        result.add(DestinationItem(result.last().end.value!!))

        result.first().removeEnabled.value = result.size > 1

        items.value = result
    }

    fun showMessage(message: String) {
        sendEvent(Event.Toast(message))
    }

//    fun done() {
//        if (title.value.isNullOrEmpty()) {
//            showMessage("Title is required")
//            return
//        }
//
//        val travel = Travel(
//            title.value,
//            description.value,
//            start.value!!.timeInMillis,
//            end.value!!.timeInMillis
//        )
//
//        launch {
//            if (travelsRepository.save(travel)) {
//                sendEvent(Event.Done)
//            } else {
//                showMessage("Error")
//            }
//        }
//    }
}