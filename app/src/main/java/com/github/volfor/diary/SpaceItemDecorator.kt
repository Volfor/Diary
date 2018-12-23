package com.github.volfor.diary

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SpaceItemDecorator(private val space: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        parent.apply {
            if (getChildAdapterPosition(view) == 0) {
                outRect.top = space
            }

            outRect.left = space
            outRect.right = space
            outRect.bottom = space
        }
    }
}