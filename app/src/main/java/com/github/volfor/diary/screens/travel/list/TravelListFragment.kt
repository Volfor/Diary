package com.github.volfor.diary.screens.travel.list

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.github.volfor.diary.R
import com.github.volfor.diary.base.BaseBoundVmFragment
import com.github.volfor.diary.databinding.FragmentTravelListBinding
import com.github.volfor.diary.extensions.toast
import com.github.volfor.diary.livedata.ViewAction
import com.github.volfor.diary.livedata.observeEvent


class TravelListFragment : BaseBoundVmFragment<FragmentTravelListBinding, TravelListViewModel>(
    R.layout.fragment_travel_list,
    TravelListViewModel::class
) {
    sealed class Event : ViewAction {
        data class Toast(val message: String) : Event()
        object NewTravel : Event()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm.init(this)
    }

    override fun initObservers() {
        vm.viewAction.observeEvent(this) {
            when (it) {
                is Event.Toast -> toast(it.message)
                is Event.NewTravel -> findNavController().navigate(R.id.action_travelListFragment_to_createTravelFragment)
            }
        }
    }
}
