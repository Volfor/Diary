package com.github.volfor.diary.screens.travel

import android.os.Bundle
import android.view.View
import com.github.volfor.diary.R
import com.github.volfor.diary.base.BaseBoundVmFragment
import com.github.volfor.diary.databinding.FragmentTravelBinding

class TravelFragment : BaseBoundVmFragment<FragmentTravelBinding, TravelViewModel>(
    R.layout.fragment_travel,
    TravelViewModel::class
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vm.travelId.value = TravelFragmentArgs.fromBundle(arguments!!).travelId
    }
}
