package com.songs.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by {Kapil Sachan} on 17/01/2021.
 */

data class BaseResponse<T>(
    @SerializedName("resultCount")
    @Expose
    var resultCount: Int? = null,
    @SerializedName("results")
    @Expose
    var results: T? = null
)