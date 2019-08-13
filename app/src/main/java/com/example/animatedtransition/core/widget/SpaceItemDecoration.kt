package com.example.animatedtransition.core.widget

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.annotation.DimenRes
import androidx.recyclerview.widget.RecyclerView

class SpaceItemDecoration(context: Context, @DimenRes resId: Int) : RecyclerView.ItemDecoration() {
    private val offset = context.resources.getDimensionPixelOffset(resId)
    private val halfOffset = offset / 2


    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val adapter = parent.adapter ?: return

        when (parent.getChildAdapterPosition(view)) {
            0 -> {
                outRect.top = offset
                outRect.bottom = halfOffset
            }
            adapter.itemCount - 1 -> {
                outRect.top = halfOffset
                outRect.bottom = halfOffset
            }
            else -> {
                outRect.top = halfOffset
                outRect.bottom = offset
            }
        }

        outRect.left = offset
        outRect.right = offset
    }
}