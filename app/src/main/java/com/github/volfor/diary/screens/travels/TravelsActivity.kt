package com.github.volfor.diary.screens.travels

import android.os.Bundle
import com.github.volfor.diary.R
import com.github.volfor.diary.base.BaseBoundVmActivity
import com.github.volfor.diary.databinding.ActivityTravelsBinding
import com.github.volfor.diary.extensions.startActivity
import com.github.volfor.diary.extensions.toast
import com.github.volfor.diary.livedata.ViewAction
import com.github.volfor.diary.livedata.observeEvent
import com.github.volfor.diary.screens.main.MainActivity
import com.github.volfor.diary.screens.travels.create.CreateTravelActivity

class TravelsActivity : BaseBoundVmActivity<ActivityTravelsBinding, TravelsViewModel>(
    R.layout.activity_travels,
    TravelsViewModel::class
) {
    sealed class Event : ViewAction {
        data class Toast(val message: String) : Event()
        object NewTravel : Event()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm.init(this)
    }

    override fun initObservers() {
        vm.viewAction.observeEvent(this) {
            when (it) {
                is Event.Toast -> toast(it.message)
                is Event.NewTravel -> startActivity(CreateTravelActivity::class)
            }
        }
    }
}
