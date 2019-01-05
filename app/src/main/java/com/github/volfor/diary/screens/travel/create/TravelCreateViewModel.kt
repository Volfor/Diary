package com.github.volfor.diary.screens.travel.create

import androidx.databinding.Bindable
import androidx.lifecycle.MutableLiveData
import com.github.ajalt.timberkt.e
import com.github.volfor.diary.BR
import com.github.volfor.diary.base.BaseEventViewModel
import com.github.volfor.diary.extensions.async
import com.github.volfor.diary.models.Travel
import com.github.volfor.diary.repositories.TravelsRepository
import com.github.volfor.diary.screens.travel.create.TravelCreateFragment.Event
import io.reactivex.rxkotlin.subscribeBy
import java.text.SimpleDateFormat
import java.util.*


class TravelCreateViewModel(
    private val travelsRepository: TravelsRepository
) : BaseEventViewModel<TravelCreateFragment.Event>() {

    val title = MutableLiveData<String>()
    val description = MutableLiveData<String>()

    var start: Calendar = Calendar.getInstance()
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.start)
            if (end.before(value)) {
                end = value.clone() as Calendar
            }
        }

    var end: Calendar = Calendar.getInstance()
        @Bindable get
        set(value) {
            field = value
            notifyPropertyChanged(BR.end)
        }

    init {
        end.add(Calendar.DAY_OF_MONTH, 7)
    }

    @Bindable("start")
    fun getStartDate(): String {
        return SimpleDateFormat("EE, MMM d", Locale.US).format(start.time)
    }

    @Bindable("end")
    fun getEndDate(): String {
        return SimpleDateFormat("EE, MMM d", Locale.US).format(end.time)
    }

    fun pickStartDate() {
        sendEvent(Event.StartDate(start))
    }

    fun pickEndDate() {
        sendEvent(Event.EndDate(end))
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
            start.timeInMillis,
            end.timeInMillis
        )

        travelsRepository.saveTravel(travel)
            .async()
            .subscribeBy(
                onNext = { successful ->
                    if (successful) {
                        sendEvent(Event.Done)
                    } else {
                        showMessage("Error")
                    }
                },
                onError = {
                    e(it)
                    showMessage("Error")
                }
            ).add()
    }
}