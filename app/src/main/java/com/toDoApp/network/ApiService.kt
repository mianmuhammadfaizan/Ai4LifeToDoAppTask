package com.toDoApp.network

import com.toDoApp.models.CallDataModel
import com.toDoApp.models.ProductDataModel
import retrofit2.http.GET

interface ApiService {
    @GET("call")
    suspend fun getCalls(): List<CallDataModel>

    @GET("buy")
    suspend fun getProduct(): List<ProductDataModel>
}