package com.example.rickandmortyapp.presentation.character_details.view

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.annotation.StringRes
import androidx.appcompat.content.res.AppCompatResources

interface ResourceManager {
    fun string(@StringRes id: Int, vararg formatArgs: String): String
    fun drawable(id: Int): Drawable?

    class Base(private val context: Context) : ResourceManager {
        override fun string(id: Int, vararg formatArgs: String): String =
            context.getString(id, *formatArgs)

        override fun drawable(id: Int): Drawable? =
            AppCompatResources.getDrawable(context, id)
    }
}