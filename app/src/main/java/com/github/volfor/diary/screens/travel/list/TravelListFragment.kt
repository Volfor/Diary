package com.github.volfor.diary.screens.travel.list

import androidx.navigation.fragment.findNavController
import com.github.volfor.diary.R
import com.github.volfor.diary.base.BaseBoundVmFragment
import com.github.volfor.diary.databinding.FragmentTravelListBinding
import com.github.volfor.diary.livedata.ViewAction
import com.github.volfor.diary.livedata.observeEvent
import com.github.volfor.diary.screens.travel.list.TravelListFragmentDirections as Directions


class TravelListFragment : BaseBoundVmFragment<FragmentTravelListBinding, TravelListViewModel>(
    R.layout.fragment_travel_list,
    TravelListViewModel::class
) {
    sealed class Event : ViewAction {
        object New : Event()
        data class Open(val travelId: String) : Event()
    }

    override fun initObservers() {
        vm.viewAction.observeEvent(this) {
            when (it) {
                is Event.New -> findNavController().navigate(Directions.actionCreateTravel())
                is Event.Open -> findNavController().navigate(Directions.actionOpenTravel(it.travelId))
            }
        }
    }
}
