package com.songs.data.model

/**
 * Created by {Kapil Sachan} on 28/12/2020.
 */

class Event<T>(private val content: T) {

    var isAlreadyHandled = false

    fun getContent(): T? {
        if (!isAlreadyHandled) {
            isAlreadyHandled = true
            return content
        }
        return null
    }

    fun peekContent(): T {
        return content
    }
}
