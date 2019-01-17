package com.github.volfor.diary.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.github.volfor.diary.R
import com.github.volfor.diary.screens.login.LoginFragmentDirections
import org.koin.androidx.viewmodel.ext.viewModel

class AppActivity : AppCompatActivity() {

    private val navController by lazy { findNavController(R.id.navHostFragment) }

    private val vm: AppViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app)

        if (vm.isLoggedIn()) {
            navController.navigate(LoginFragmentDirections.actionOpenTravelList())
        }
    }
}