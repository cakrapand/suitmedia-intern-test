package com.example.suitmedia.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.suitmedia.data.remote.response.User
import com.example.suitmedia.data.remote.retrofit.ApiService
import kotlinx.coroutines.flow.flow

class Repository private constructor(private val apiService: ApiService) {

//    fun getUsers() = flow {
//        emit(Result.Loading)
//        try {
//            val pager = Pager(config = PagingConfig(pageSize = 10), pagingSourceFactory = { UserPagingSource(apiService) }).liveData
//            emit(Result.Success(pager))
//        } catch (e: Exception) {
//            Log.d("Repository", "getUser: ${e.message.toString()}")
//            emit(Result.Error(e.message.toString()))
//        }
//    }

    fun getUsers(): LiveData<PagingData<User>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = { UserPagingSource(apiService) }
        ).liveData
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