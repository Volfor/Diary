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
        data class StartDate(val calendar: Calendar) : Event()
        data class EndDate(val calendar: Calendar) : Event()
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
                is Event.StartDate -> showStartDatePicker(it.calendar)
                is Event.EndDate -> showEndDatePicker(it.calendar)
            }
        }
    }

    private fun showStartDatePicker(calendar: Calendar) {
        DatePickerDialog(
            requireContext(),
            { _, year, month, day ->
                vm.start = calendar.update(year, month, day)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    private fun showEndDatePicker(calendar: Calendar) {
        DatePickerDialog(
            requireContext(),
            { _, year, month, day ->
                vm.end = calendar.update(year, month, day)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).apply {
            datePicker.minDate = vm.start.timeInMillis
        }.show()
    }
}
