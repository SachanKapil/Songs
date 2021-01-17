package com.songs.data.api

import com.songs.data.model.BaseResponse
import com.songs.data.model.songs_list.SongDetailItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by {Kapil Sachan} on 28/12/2020.
 */

interface ApiClient {
    @GET("search")
    fun hitGetSongsListApi(
        @Query("term") term: String,
    ): Call<BaseResponse<ArrayList<SongDetailItem>>>
}