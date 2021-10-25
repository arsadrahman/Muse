package com.arsa.muse.repository

import androidx.lifecycle.MutableLiveData
import com.arsa.muse.model.DataList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class Repository  @Inject constructor(private val networkInterface: NetworkInterface) {
    suspend fun getList() = networkInterface.getList()

}