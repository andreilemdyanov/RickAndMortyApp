package com.example.rickandmortyapp.presentation.character_list.view

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class LinearSpacingItemDecoration(private val spacing: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.bottom = spacing
        outRect.top = spacing
    }
}