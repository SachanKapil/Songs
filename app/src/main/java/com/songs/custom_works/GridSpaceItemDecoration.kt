package com.songs.custom_works

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.songs.constants.AppConstants

class GridSpaceItemDecoration(
    private val left: Int, private val top: Int, private val right: Int, private val bottom: Int
) : ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView, state: RecyclerView.State
    ) {
        val currentItemPosition = parent.getChildAdapterPosition(view)
        val itemViewType = parent.adapter?.getItemViewType(currentItemPosition)
        itemViewType?.let {
            if (it != AppConstants.ViewTypeConstants.VIEW_TYPE_LOADING) {
                if (currentItemPosition % 2 == 0) {
                    outRect.left = left
                    outRect.right = right
                    outRect.bottom = bottom
                    outRect.top = top
                } else {
                    outRect.left = right
                    outRect.right = left
                    outRect.bottom = bottom
                    outRect.top = top
                }
            }
        }
    }
}

