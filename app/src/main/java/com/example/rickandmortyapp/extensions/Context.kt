package com.example.rickandmortyapp.extensions

import android.content.Context
import android.util.TypedValue

fun Context.dpToIntPx(dp: Int): Int {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dp.toFloat(),
        this.resources.displayMetrics
    ).toInt()
}

const val EMPTY_STRING = ""
const val EMPTY_INT = 0