package com.songs.data.model

/**
 * Created by {Kapil Sachan} on 28/12/2020.
 */

data class WrappedResponse<T>(
    var data: T? = null,
    var resultCount: Int? = null,
    var failureResponse: FailureResponse? = null
)