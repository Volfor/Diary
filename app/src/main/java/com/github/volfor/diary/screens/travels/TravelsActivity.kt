package com.github.volfor.diary.screens.travels

import com.github.volfor.diary.base.BaseBoundVmActivity
import com.github.volfor.diary.databinding.ActivityTravelsBinding

class TravelsActivity : BaseBoundVmActivity<ActivityTravelsBinding, TravelsViewModel>(
    com.github.volfor.diary.R.layout.activity_travels,
    TravelsViewModel::class
)