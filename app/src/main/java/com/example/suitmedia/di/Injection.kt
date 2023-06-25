package com.example.suitmedia.di

import com.example.suitmedia.data.Repository
import com.example.suitmedia.data.remote.retrofit.ApiConfig

object Injection {
    fun provideRepository(): Repository {
        val apiService = ApiConfig.getApiService()
        return Repository.getInstance(apiService)
    }
}