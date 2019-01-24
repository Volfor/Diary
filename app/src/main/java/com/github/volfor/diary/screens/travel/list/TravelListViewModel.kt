package com.github.volfor.diary.screens.travel.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.recyclerview.widget.DiffUtil
import com.github.volfor.diary.BR
import com.github.volfor.diary.CoroutineContextHolder
import com.github.volfor.diary.R
import com.github.volfor.diary.base.BaseEventViewModel
import com.github.volfor.diary.repositories.TravelsRepository
import com.github.volfor.diary.screens.travel.list.TravelListFragment.Event
import me.tatarka.bindingcollectionadapter2.itembindings.OnItemBindClass
import me.tatarka.bindingcollectionadapter2.map
import java.util.*


class TravelListViewModel(
    ctx: CoroutineContextHolder,
    private val travelsRepository: TravelsRepository
) : BaseEventViewModel<TravelListFragment.Event>(ctx), TravelItem.Listener {

    val items: LiveData<List<Any>> = getSortedTravels()

    val itemsBinding = OnItemBindClass<Any>().apply {
        map<String>(BR.item, R.layout.item_header)
        map<TravelItem> { itemBinding, _, _ ->
            itemBinding.set(BR.item, R.layout.item_travel)
                .bindExtra(BR.listener, this@TravelListViewModel)
        }
    }

    val diff: DiffUtil.ItemCallback<Any> = object : DiffUtil.ItemCallback<Any>() {
        override fun areItemsTheSame(old: Any, new: Any): Boolean {
            return if (old is TravelItem && new is TravelItem) {
                old.travel.id == new.travel.id
            } else old == new
        }

        override fun areContentsTheSame(old: Any, new: Any): Boolean {
            return old == new
        }
    }

    override fun onClick(item: TravelItem) {
        item.travel.id?.let { sendEvent(Event.Open(it)) }
    }

    fun createTravel() {
        sendEvent(Event.New)
    }

    private fun getSortedTravels() = Transformations.map(travelsRepository.loadTravels()) { travels ->
        val time = Calendar.getInstance().timeInMillis

        val current = mutableListOf<TravelItem>()
        val upcoming = mutableListOf<TravelItem>()
        val past = mutableListOf<TravelItem>()

        travels.forEach { travel ->
            when {
                travel.start <= time && travel.end >= time -> current.add(TravelItem(travel))
                travel.start > time -> upcoming.add(TravelItem(travel))
                travel.end < time -> past.add(TravelItem(travel))
            }
        }

        val result = mutableListOf<Any>().apply {
            if (current.isNotEmpty()) {
                add("Current")
                addAll(current)
            }
            if (upcoming.isNotEmpty()) {
                add("Upcoming")
                addAll(upcoming)
            }
            if (past.isNotEmpty()) {
                add("Past")
                addAll(past)
            }
        }

        result.toList()
    }
}