package com.songs.data.model.songs_list

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SongDetailItem(
    @SerializedName("artistId")
    var artistId: Int? = null,
    @SerializedName("artistName")
    var artistName: String? = null,
    @SerializedName("artistViewUrl")
    var artistViewUrl: String? = null,
    @SerializedName("artworkUrl100")
    var artworkUrl100: String? = null,
    @SerializedName("artworkUrl30")
    var artworkUrl30: String? = null,
    @SerializedName("artworkUrl60")
    var artworkUrl60: String? = null,
    @SerializedName("collectionArtistId")
    var collectionArtistId: Int? = null,
    @SerializedName("collectionArtistName")
    var collectionArtistName: String? = null,
    @SerializedName("collectionArtistViewUrl")
    var collectionArtistViewUrl: String? = null,
    @SerializedName("collectionCensoredName")
    var collectionCensoredName: String? = null,
    @SerializedName("collectionExplicitness")
    var collectionExplicitness: String? = null,
    @SerializedName("collectionId")
    var collectionId: Int? = null,
    @SerializedName("collectionName")
    var collectionName: String? = null,
    @SerializedName("collectionPrice")
    var collectionPrice: Double? = null,
    @SerializedName("collectionViewUrl")
    var collectionViewUrl: String? = null,
    @SerializedName("copyright")
    var copyright: String? = null,
    @SerializedName("country")
    var country: String? = null,
    @SerializedName("currency")
    var currency: String? = null,
    @SerializedName("description")
    var description: String? = null,
    @SerializedName("discCount")
    var discCount: Int? = null,
    @SerializedName("discNumber")
    var discNumber: Int? = null,
    @SerializedName("isStreamable")
    var isStreamable: Boolean? = null,
    @SerializedName("kind")
    var kind: String? = null,
    @SerializedName("previewUrl")
    var previewUrl: String? = null,
    @SerializedName("primaryGenreName")
    var primaryGenreName: String? = null,
    @SerializedName("releaseDate")
    var releaseDate: String? = null,
    @SerializedName("trackCensoredName")
    var trackCensoredName: String? = null,
    @SerializedName("trackCount")
    var trackCount: Int? = null,
    @SerializedName("trackExplicitness")
    var trackExplicitness: String? = null,
    @SerializedName("trackId")
    var trackId: Int? = null,
    @SerializedName("trackName")
    var trackName: String? = null,
    @SerializedName("trackNumber")
    var trackNumber: Int? = null,
    @SerializedName("trackPrice")
    var trackPrice: Double? = null,
    @SerializedName("trackTimeMillis")
    var trackTimeMillis: Int? = null,
    @SerializedName("trackViewUrl")
    var trackViewUrl: String? = null,
    @SerializedName("wrapperType")
    var wrapperType: String? = null
) : Parcelable