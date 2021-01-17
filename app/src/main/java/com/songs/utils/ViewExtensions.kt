package com.songs.utils

import android.view.View

/**
 * Created by {Kapil Sachan} on 28/12/2020.
 */


fun View.visible() {
    visibility = View.VISIBLE
}

fun View.inVisible() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.select() {
    isSelected = true
}

fun View.unSelect() {
    isSelected = false
}

fun View.enable() {
    isEnabled = true
}

fun View.disable() {
    isEnabled = false
}

