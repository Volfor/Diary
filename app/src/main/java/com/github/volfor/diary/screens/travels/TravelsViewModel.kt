package com.github.volfor.diary.screens.travels

import com.github.volfor.diary.BR
import com.github.volfor.diary.R
import com.github.volfor.diary.base.BaseViewModel
import com.github.volfor.diary.extensions.observableListOf
import com.github.volfor.diary.livedata.ViewAction
import com.github.volfor.diary.models.Travel
import com.github.volfor.diary.screens.travels.item.TravelItem
import me.tatarka.bindingcollectionadapter2.ItemBinding

class TravelsViewModel : BaseViewModel<ViewAction>() {

    val items = observableListOf<TravelItem>()
    val itemBinding: ItemBinding<TravelItem> = ItemBinding.of(BR.item, R.layout.item_travel)

    init {
        for (i in 0..10) {
            items.add(TravelItem(Travel("Travel to Country #$i")))
        }
    }
}