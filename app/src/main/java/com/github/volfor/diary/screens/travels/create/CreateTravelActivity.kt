package com.github.volfor.diary.screens.travels.create

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.DatePicker
import androidx.databinding.adapters.DatePickerBindingAdapter
import com.github.volfor.diary.R
import com.github.volfor.diary.base.BaseBoundVmActivity
import com.github.volfor.diary.databinding.ActivityCreateTravelBinding
import com.github.volfor.diary.extensions.*
import com.github.volfor.diary.livedata.ViewAction
import com.github.volfor.diary.livedata.observeEvent
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_create_travel.*
import java.time.Year
import java.util.*

class CreateTravelActivity : BaseBoundVmActivity<ActivityCreateTravelBinding, CreateTravelViewModel>(
    R.layout.activity_create_travel,
    CreateTravelViewModel::class
) {
    sealed class Event : ViewAction {
        object Finish : Event()
        data class Toast(val message: String) : Event()
        data class StartDate(val calendar: Calendar) : Event()
        data class EndDate(val calendar: Calendar) : Event()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupToolbar()
    }

    override fun initObservers() {
        vm.viewAction.observeEvent(this) {
            when (it) {
                is Event.Finish -> finish()
                is Event.Toast -> toast(it.message)
                is Event.StartDate -> showStartDatePicker(it.calendar)
                is Event.EndDate -> showEndDatePicker(it.calendar)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.title = "Create Travel"
        }
    }

    private fun showStartDatePicker(calendar: Calendar) {
        DatePickerDialog(this).apply {
            updateDate(
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )

            setOnDateSetListener { _, year, month, day ->
                vm.start = calendar.update(year, month, day)
            }

            show()
        }
    }

    private fun showEndDatePicker(calendar: Calendar) {
        DatePickerDialog(this).apply {
            updateDate(
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )

            setOnDateSetListener { _, year, month, day ->
                vm.end = calendar.update(year, month, day)
            }

            datePicker.minDate = vm.start.timeInMillis

            show()
        }
    }
}