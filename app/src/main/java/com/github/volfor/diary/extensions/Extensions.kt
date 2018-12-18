package com.github.volfor.diary.extensions

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import androidx.fragment.app.Fragment
import kotlin.reflect.KClass

fun Activity.startActivity(cls: KClass<*>) {
    startActivity(Intent(this, cls.java))
}

fun Fragment.startActivity(cls: KClass<*>) {
    startActivity(Intent(context, cls.java))
}

fun Activity.startActivityAndFinish(cls: KClass<*>) {
    startActivity(cls)
    finish()
}

fun Fragment.startActivityAndFinish(cls: KClass<*>) {
    startActivity(cls)
    activity?.finish()
}

fun Activity.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Fragment.toast(message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}