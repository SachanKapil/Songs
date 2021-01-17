package com.songs.ui.home.songs_listing

import androidx.lifecycle.MutableLiveData
import com.songs.base.NetworkCallback
import com.songs.data.api.ApiManager
import com.songs.data.model.Event
import com.songs.data.model.FailureResponse
import com.songs.data.model.WrappedResponse
import com.songs.data.model.songs_list.GetSongsListRequest
import com.songs.data.model.songs_list.SongDetailItem

class SongsListingRepo {

    internal fun hitGetSongsListApi(getSongsListRequest: GetSongsListRequest): MutableLiveData<Event<WrappedResponse<ArrayList<SongDetailItem>>>> {

        val songsListResponseLiveData =
            MutableLiveData<Event<WrappedResponse<ArrayList<SongDetailItem>>>>()
        val wrappedResponse = WrappedResponse<ArrayList<SongDetailItem>>()

        ApiManager.hitGetSongsListApi(getSongsListRequest)
            .enqueue(object : NetworkCallback<ArrayList<SongDetailItem>>() {
                override fun onSuccess(
                    t: ArrayList<SongDetailItem>?,
                    resultCount: Int?
                ) {
                    wrappedResponse.data = t
                    wrappedResponse.resultCount = resultCount
                    songsListResponseLiveData.value = Event(wrappedResponse)
                }

                override fun onFailure(failureResponse: FailureResponse) {
                    wrappedResponse.failureResponse = failureResponse
                    songsListResponseLiveData.value = Event(wrappedResponse)
                }

                override fun onError(t: Throwable) {
                    wrappedResponse.failureResponse = FailureResponse.genericError()
                    songsListResponseLiveData.value = Event(wrappedResponse)
                }
            })

        return songsListResponseLiveData
    }

}