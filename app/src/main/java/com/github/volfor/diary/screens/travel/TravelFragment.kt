package com.github.volfor.diary.screens.travel

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.github.volfor.diary.R
import com.github.volfor.diary.base.BaseBoundVmFragment
import com.github.volfor.diary.databinding.FragmentTravelBinding
import kotlinx.android.synthetic.main.fragment_travel.*

class TravelFragment : BaseBoundVmFragment<FragmentTravelBinding, TravelViewModel>(
    R.layout.fragment_travel,
    TravelViewModel::class
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar.setupWithNavController(findNavController())

        vm.init(TravelFragmentArgs.fromBundle(arguments!!).travelId)
    }
}
