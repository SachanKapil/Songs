package com.songs.base

import com.songs.constants.AppConstants
import com.songs.data.model.BaseResponse
import com.songs.data.model.FailureResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * Created by {Kapil Sachan} on 28/12/2020.
 */

abstract class NetworkCallback<T> : Callback<BaseResponse<T>> {

    abstract fun onSuccess(t: T?, resultCount: Int?)

    abstract fun onFailure(failureResponse: FailureResponse)

    abstract fun onError(t: Throwable)

    override fun onResponse(call: Call<BaseResponse<T>>, response: Response<BaseResponse<T>>) {
        if (response.isSuccessful && response.body() != null) {
            onSuccess(
                (response.body() as BaseResponse).results,
                (response.body() as BaseResponse).resultCount
            )
        } else {
            onFailure(FailureResponse.genericError())
        }
    }

    override fun onFailure(call: Call<BaseResponse<T>?>?, t: Throwable) {
        if (t is SocketTimeoutException || t is UnknownHostException) {
            val failureResponseForNoNetwork: FailureResponse = getFailureResponseForNoNetwork()
            onFailure(failureResponseForNoNetwork)
        } else if (t is ConnectException) {
            val failureResponseForUnableToConnect: FailureResponse =
                getFailureResponseForUnableToConnect()
            onFailure(failureResponseForUnableToConnect)
        } else {
            onError(t)
        }
    }

    private fun getFailureResponseForNoNetwork(): FailureResponse {
        return FailureResponse(
            AppConstants.NetworkingConstants.NO_INTERNET_CONNECTION,
            "No Internet Connection"
        )
    }

    private fun getFailureResponseForUnableToConnect(): FailureResponse {
        return FailureResponse(
            AppConstants.NetworkingConstants.INTERNAL_SERVER_ERROR_CODE,
            "Unable to connect to server"
        )
    }
}