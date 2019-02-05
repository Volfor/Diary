package com.github.volfor.diary.toolbar

import android.view.MenuItem
import androidx.annotation.MenuRes

data class ToolbarData(
    val title: String,
    val subtitle: String? = null,
    @MenuRes val menuResId: Int = 0,
    val elevation: Int = -1,
    val items: List<ToolbarItem>? = null
)

data class ToolbarItem(
    val itemId: Int,
    val iconTint: Int? = null,
    val customResId: Int? = null,
    val onClick: ((item: MenuItem) -> Unit)? = null
)