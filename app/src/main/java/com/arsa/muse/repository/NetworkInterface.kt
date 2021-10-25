package com.arsa.muse.repository

import com.arsa.muse.model.Data
import com.arsa.muse.model.DataList
import retrofit2.Call
import retrofit2.http.GET

public interface NetworkInterface {
    @GET("/v1/test/route/animation")
    suspend fun getList():DataList
}