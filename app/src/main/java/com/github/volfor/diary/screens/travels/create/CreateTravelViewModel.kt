package com.github.volfor.diary.screens.travels.create

import androidx.lifecycle.MutableLiveData
import com.github.volfor.diary.base.BaseViewModel
import com.github.volfor.diary.extensions.async
import com.github.volfor.diary.livedata.ViewAction
import com.github.volfor.diary.models.Travel
import com.github.volfor.diary.repositories.TravelsRepository
import com.github.volfor.diary.screens.travels.create.CreateTravelActivity.Event

class CreateTravelViewModel(
    private val travelsRepository: TravelsRepository
) : BaseViewModel<ViewAction>() {

    val title = MutableLiveData<String>()
    val description = MutableLiveData<String>()

    fun done() {
        if (title.value.isNullOrEmpty()) {
            _viewAction.value = Event.Toast("Title is required")
            return
        }

        subscribe(
            travelsRepository.saveTravel(Travel(title.value, description.value))
                .async()
                .subscribe({ success ->
                    if (success) {
                        _viewAction.value = Event.Finish
                    } else {
                        _viewAction.value = Event.Toast("Error")
                    }
                }, {
                    _viewAction.value = Event.Toast("Error")
                })
        )
    }
}