package com.grofin.feature.dashboard.service.adapter

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ItemDecorationAlbumColumns(private val mSizeGridSpacingPx: Int, private val mGridSize: Int) :
    RecyclerView.ItemDecoration() {
    private var mNeedLeftSpacing = false

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val frameWidth =
            ((parent.width - mSizeGridSpacingPx.toFloat() * (mGridSize - 1)).toInt() / mGridSize)

        val padding: Int = parent.width / mGridSize - frameWidth

        val itemPosition: Int =
            (view.layoutParams as RecyclerView.LayoutParams).bindingAdapterPosition

        if (itemPosition < mGridSize)
            outRect.top = 0
        else
            outRect.top = mSizeGridSpacingPx

        if (itemPosition % mGridSize == 0) {
            outRect.left = 0
            outRect.right = padding
            mNeedLeftSpacing = true
        } else if ((itemPosition + 1) % mGridSize == 0) {
            mNeedLeftSpacing = false
            outRect.right = 0
            outRect.left = padding
        } else if (mNeedLeftSpacing) {
            mNeedLeftSpacing = false
            outRect.left = mSizeGridSpacingPx - padding
            if ((itemPosition + 2) % mGridSize == 0)
                outRect.right = mSizeGridSpacingPx - padding
            else
                outRect.right = mSizeGridSpacingPx / 2

        } else if ((itemPosition + 2) % mGridSize == 0) {
            mNeedLeftSpacing = false
            outRect.left = mSizeGridSpacingPx / 2
            outRect.right = mSizeGridSpacingPx - padding
        } else {
            mNeedLeftSpacing = false
            outRect.left = mSizeGridSpacingPx / 2
            outRect.right = mSizeGridSpacingPx / 2
        }
        outRect.bottom = 0
    }
}