package com.songs.ui.home.songs_listing

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.songs.data.model.Event
import com.songs.data.model.WrappedResponse
import com.songs.data.model.songs_list.GetSongsListRequest
import com.songs.data.model.songs_list.SongDetailItem

class SongsListingViewModel : ViewModel() {

    private val repo = SongsListingRepo()

    private val songsListRequestLiveData = MutableLiveData<GetSongsListRequest>()
    private val songsListResponseLiveData =
        Transformations.switchMap(songsListRequestLiveData) { request ->
            repo.hitGetSongsListApi(
                request
            )
        }

    fun getSongsListResponseLiveData(): LiveData<Event<WrappedResponse<ArrayList<SongDetailItem>>>> {
        return songsListResponseLiveData
    }

    fun hitGetSongsListApi() {
        val getSongsListRequest = GetSongsListRequest("Michael+jackson")
        songsListRequestLiveData.value = getSongsListRequest
    }
}