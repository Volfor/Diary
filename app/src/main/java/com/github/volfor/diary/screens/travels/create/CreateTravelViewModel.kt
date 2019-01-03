package com.github.volfor.diary.screens.travels.create

import androidx.databinding.Bindable
import androidx.lifecycle.MutableLiveData
import com.github.ajalt.timberkt.e
import com.github.volfor.diary.BR
import com.github.volfor.diary.base.BaseViewModel
import com.github.volfor.diary.extensions.async
import com.github.volfor.diary.livedata.ViewAction
import com.github.volfor.diary.models.Travel
import com.github.volfor.diary.repositories.TravelsRepository
import com.github.volfor.diary.screens.travels.create.CreateTravelActivity.Event
import io.reactivex.rxkotlin.subscribeBy
import java.text.SimpleDateFormat
import java.util.*


class CreateTravelViewModel(
    private val travelsRepository: TravelsRepository
) : BaseViewModel<ViewAction>() {

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
        _viewAction.value = Event.StartDate(start)
    }

    fun pickEndDate() {
        _viewAction.value = Event.EndDate(end)
    }

    fun done() {
        if (title.value.isNullOrEmpty()) {
            _viewAction.value = Event.Toast("Title is required")
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
                        _viewAction.value = Event.Finish
                    } else {
                        _viewAction.value = Event.Toast("Error")
                    }
                },
                onError = {
                    e(it)
                    _viewAction.value = Event.Toast("Error")
                }
            ).add()
    }
}