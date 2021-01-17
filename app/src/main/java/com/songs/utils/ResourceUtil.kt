package com.songs.utils

import android.content.Context
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.songs.SongsApp
import com.songs.R

/**
 * Created by {Kapil Sachan} on 17/01/2021.
 */

object ResourceUtil {

    fun getResourceIdFromResourceName(drawableName: String): Int {
        try {
            val res = R.drawable::class.java
            val field = res.getField(drawableName)
            return field.getInt(null)
        } catch (e: Exception) {

        }
        return -1
    }

    fun getDrawable(resId: Int): Drawable? {
        return ContextCompat.getDrawable(SongsApp.appContext, resId)
    }

    fun getColor(colorResId: Int): Int {
        return ContextCompat.getColor(SongsApp.appContext, colorResId)
    }

    fun getString(resId: Int): String {
        return SongsApp.appContext.getString(resId)
    }

    fun getString(context: Context, resId: Int): String {
        return context.getString(resId)
    }

    fun getStringArray(resId: Int): ArrayList<String> {
        val stringArray: Array<String> =
            SongsApp.appContext.resources.getStringArray(resId)
        return stringArray.toCollection(ArrayList())
    }

    fun getFont(font: Int): Typeface? {
        return ResourcesCompat.getFont(SongsApp.appContext, font)
    }
}