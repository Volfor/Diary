package com.github.volfor.diary.extensions

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import java.util.*
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

inline fun <reified T> observableListOf(): ObservableList<T> = ObservableArrayList<T>()

fun Calendar.update(year: Int, month: Int, day: Int): Calendar {
    set(Calendar.YEAR, year)
    set(Calendar.MONTH, month)
    set(Calendar.DAY_OF_MONTH, day)

    return this
}

fun <X, Y> LiveData<X>.map(mapFunc: (input: X) -> Y): LiveData<Y> {
    return Transformations.map(this, mapFunc)
}