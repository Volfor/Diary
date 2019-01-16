package com.github.volfor.diary.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.github.volfor.diary.R
import com.github.volfor.diary.screens.login.LoginFragmentDirections
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.direct
import org.kodein.di.generic.instance

class AppActivity : AppCompatActivity(), KodeinAware {

    override val kodein: Kodein by closestKodein()

    private val navController by lazy { findNavController(R.id.navHostFragment) }

    private val vm: AppViewModel by lazy {
        ViewModelProviders.of(this, direct.instance()).get(AppViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app)

        if (vm.isLoggedIn()) {
            navController.navigate(LoginFragmentDirections.actionOpenTravelList())
        }
    }
}