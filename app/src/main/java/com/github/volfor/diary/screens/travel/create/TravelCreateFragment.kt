package com.github.volfor.diary.screens.travel.create

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.github.volfor.diary.R
import com.github.volfor.diary.base.BaseBoundVmFragment
import com.github.volfor.diary.databinding.FragmentTravelCreateBinding
import com.github.volfor.diary.extensions.toast
import com.github.volfor.diary.extensions.update
import com.github.volfor.diary.livedata.ViewAction
import com.github.volfor.diary.livedata.observeEvent
import kotlinx.android.synthetic.main.fragment_travel_create.*
import java.util.*


class TravelCreateFragment : BaseBoundVmFragment<FragmentTravelCreateBinding, TravelCreateViewModel>(
    R.layout.fragment_travel_create,
    TravelCreateViewModel::class
) {
    sealed class Event : ViewAction {
        object Done : Event()
        data class Toast(val message: String) : Event()
        data class StartDate(val item: DestinationItem) : Event()
        data class EndDate(val item: DestinationItem) : Event()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar.setupWithNavController(findNavController())
    }

    override fun initObservers() {
        vm.viewAction.observeEvent(this) {
            when (it) {
                is Event.Done -> findNavController().popBackStack()
                is Event.Toast -> toast(it.message)
                is Event.StartDate -> showStartDatePicker(it.item)
                is Event.EndDate -> showEndDatePicker(it.item)
            }
        }
    }

    private fun showStartDatePicker(item: DestinationItem) {
        val calendar = item.start.value!!

        DatePickerDialog(
            requireContext(),
            { _, year, month, day -> item.updateStartDate(calendar.update(year, month, day)) },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    private fun showEndDatePicker(item: DestinationItem) {
        val calendar = item.end.value!!
        val minDate = item.start.value!!.timeInMillis

        DatePickerDialog(
            requireContext(),
            { _, year, month, day -> item.updateEndDate(calendar.update(year, month, day)) },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).apply {
            datePicker.minDate = minDate
        }.show()
    }
}
