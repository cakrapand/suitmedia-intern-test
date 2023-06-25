package com.example.suitmedia.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.suitmedia.data.remote.response.User
import com.example.suitmedia.data.remote.retrofit.ApiService
import kotlinx.coroutines.flow.Flow

class Repository private constructor(private val apiService: ApiService) {

    fun getUsers(): Flow<PagingData<User>> {
        return Pager(
            config = PagingConfig(pageSize = 5),
            pagingSourceFactory = { UserPagingSource(apiService) }
        ).flow
    }

    companion object {
        @Volatile
        private var instance: Repository? = null
        fun getInstance(
            apiService: ApiService,
        ): Repository = instance ?: synchronized(this) {
            instance ?: Repository(apiService)
        }.also { instance = it }
    }
}