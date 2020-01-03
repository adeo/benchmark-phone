package com.mobility.benchmark.network

import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Streaming

interface api {

    @Headers("Cache-control: no-cache")
    @Streaming
    @GET("/files/1Gb.dat")
    fun downloadFile(): Observable<Response<ResponseBody>>
}