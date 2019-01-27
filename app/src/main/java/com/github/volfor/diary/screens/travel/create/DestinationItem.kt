package com.github.volfor.diary.screens.travel.create

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import java.text.SimpleDateFormat
import java.util.*

data class DestinationItem(
    private val startValue: Calendar,
    private val canDelete: Boolean = true
) {

    interface Listener {
        fun pickStart(item: DestinationItem)

        fun pickEnd(item: DestinationItem)

        fun remove(item: DestinationItem)
    }

    val removeEnabled = MutableLiveData<Boolean>().apply {
        value = canDelete
    }

    val location = MutableLiveData<String>()

    val start = MutableLiveData<Calendar>().apply {
        value = startValue
    }

    val end = MutableLiveData<Calendar>().apply {
        value = (startValue.clone() as Calendar).apply {
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

}