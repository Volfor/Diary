package com.github.volfor.diary.screens.travel.create

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.github.volfor.diary.CoroutineContextHolder
import com.github.volfor.diary.base.BaseEventViewModel
import com.github.volfor.diary.models.Travel
import com.github.volfor.diary.repositories.TravelsRepository
import com.github.volfor.diary.screens.travel.create.TravelCreateFragment.Event
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*


class TravelCreateViewModel(
    ctx: CoroutineContextHolder,
    private val travelsRepository: TravelsRepository
) : BaseEventViewModel<TravelCreateFragment.Event>(ctx) {

    val title = MutableLiveData<String>()
    val description = MutableLiveData<String>()

    val start = MutableLiveData<Calendar>().apply {
        value = Calendar.getInstance()
    }

    val end = MutableLiveData<Calendar>().apply {
        value = Calendar.getInstance().apply {
            add(Calendar.DAY_OF_MONTH, 7)
        }
    }

    val startDate: LiveData<String> = Transformations.map(start) {
        SimpleDateFormat("EE, MMM d", Locale.US).format(it.time)
    }

    val endDate: LiveData<String> = Transformations.map(end) {
        SimpleDateFormat("EE, MMM d", Locale.US).format(it.time)
    }

    fun updateStartDate(calendar: Calendar) {
        start.value = calendar

        if (end.value?.before(calendar) == true) {
            end.value = calendar.clone() as Calendar
        }
    }

    fun updateEndDate(calendar: Calendar) {
        end.value = calendar
    }

    fun pickStartDate() {
        sendEvent(Event.StartDate(start.value!!))
    }

    fun pickEndDate() {
        sendEvent(Event.EndDate(end.value!!, start.value!!.timeInMillis))
    }

    fun showMessage(message: String) {
        sendEvent(Event.Toast(message))
    }

    fun done() {
        if (title.value.isNullOrEmpty()) {
            showMessage("Title is required")
            return
        }

        val travel = Travel(
            title.value,
            description.value,
            start.value!!.timeInMillis,
            end.value!!.timeInMillis
        )

        launch {
            if (travelsRepository.save(travel)) {
                sendEvent(Event.Done)
            } else {
                showMessage("Error")
            }
        }
    }
}