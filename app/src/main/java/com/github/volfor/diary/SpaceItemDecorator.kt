package com.github.volfor.diary

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SpaceItemDecorator(private val space: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        with(outRect) {
            if (parent.getChildAdapterPosition(view) == 0) {
                top = space
            }

            left = space
            right = space
            bottom = space
        }
    }
}