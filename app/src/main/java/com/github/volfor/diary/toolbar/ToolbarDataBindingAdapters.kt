@file:BindingMethods(
    BindingMethod(type = Toolbar::class, attribute = "onMenuItemClick", method = "setOnMenuItemClickListener"),
    BindingMethod(type = Toolbar::class, attribute = "onNavigationClick", method = "setNavigationOnClickListener")
)

package com.github.volfor.diary.toolbar

import androidx.annotation.ColorInt
import androidx.appcompat.widget.Toolbar
import androidx.core.graphics.drawable.DrawableCompat
import androidx.databinding.BindingAdapter
import androidx.databinding.BindingMethod
import androidx.databinding.BindingMethods

@BindingAdapter("toolbarData")
fun setToolbarData(toolbar: Toolbar, toolbarData: ToolbarData?) {
    toolbar.apply {
        title = ""
        menu.clear()
        if (toolbarData != null) {
            with(toolbarData) {
                toolbar.title = title

                toolbar.subtitle = subtitle

                if (elevation >= 0) {
                    setElevation(elevation.toFloat())
                }

                if (menuResId != 0) {
                    inflateMenu(menuResId)
                    if (items != null) {
                        items.forEach { item ->
                            if (item.iconTint != null) {
                                val menuItem = menu.findItem(item.itemId)
                                DrawableCompat.setTint(menuItem.icon.mutate(), item.iconTint)
                            }

                            if (item.customResId != null) {
                                menu.findItem(item.itemId).setIcon(item.customResId)
                            }
                        }

                        setOnMenuItemClickListener { menuItem ->
                            items.forEach {
                                if (it.itemId == menuItem.itemId) {
                                    it.onClick?.invoke(menuItem)
                                }
                            }
                            return@setOnMenuItemClickListener false
                        }
                    }
                } else {
                    menu.clear()
                }
            }
        }
    }
}

@BindingAdapter("navIconTint")
fun setNavIconTint(toolbar: Toolbar, @ColorInt tint: Int) {
    toolbar.navigationIcon?.let {
        val wrappedDrawable = DrawableCompat.wrap(it)
        toolbar.navigationIcon = wrappedDrawable
        DrawableCompat.setTint(wrappedDrawable, tint)
    }
}

@BindingAdapter("onNavigationClick")
fun onNavigationClick(toolbar: Toolbar, listener: Runnable) {
    toolbar.setNavigationOnClickListener { listener.run() }
}