package com.github.volfor.diary.screens.travels.create

import com.github.volfor.diary.R
import com.github.volfor.diary.base.BaseBoundVmActivity
import com.github.volfor.diary.databinding.ActivityCreateTravelBinding
import com.github.volfor.diary.extensions.toast
import com.github.volfor.diary.livedata.ViewAction
import com.github.volfor.diary.livedata.observeEvent

class CreateTravelActivity : BaseBoundVmActivity<ActivityCreateTravelBinding, CreateTravelViewModel>(
    R.layout.activity_create_travel,
    CreateTravelViewModel::class
) {
    sealed class Event : ViewAction {
        object Finish : Event()
        data class Toast(val message: String) : Event()
    }

    override fun initObservers() {
        vm.viewAction.observeEvent(this) {
            when (it) {
                is Event.Finish -> finish()
                is Event.Toast -> toast(it.message)
            }
        }
    }
}