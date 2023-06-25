package com.example.suitmedia.data.remote.retrofit

import com.example.suitmedia.data.remote.response.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("users")
    suspend fun getListUsers(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
    ): Response
}