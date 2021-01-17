package com.songs.data.model

/**
 * Created by {Kapil Sachan} on 28/12/2020.
 */

data class FailureResponse(
    var errorCode: Int? = null,
    var errorMessage: String? = null
) {
    companion object {
        fun genericError(): FailureResponse {
            return FailureResponse(1818, "Something went wrong")
        }
    }
}