package com.github.volfor.diary.screens.travels

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.OnRebindCallback
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.github.volfor.diary.BR
import com.github.volfor.diary.base.BaseBoundVmActivity
import com.github.volfor.diary.databinding.ActivityTravelsBinding
import com.github.volfor.diary.databinding.ItemTravelBinding
import com.github.volfor.diary.models.Travel
import kotlinx.android.synthetic.main.activity_travels.*
import me.tatarka.bindingcollectionadapter2.ItemBinding


class TravelsActivity : BaseBoundVmActivity<ActivityTravelsBinding, TravelsViewModel>(
    com.github.volfor.diary.R.layout.activity_travels,
    TravelsViewModel::class
) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm.init(this)
    }
}
