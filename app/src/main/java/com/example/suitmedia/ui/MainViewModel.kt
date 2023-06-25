package com.example.suitmedia.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.suitmedia.data.Repository
import com.example.suitmedia.data.remote.response.User
import kotlinx.coroutines.flow.collect

class MainViewModel(private val repository: Repository) : ViewModel() {

    private var _listUser = MutableLiveData<PagingData<User>>()
    val listUser : LiveData<PagingData<User>>
        get() = _listUser

    private val observer  = Observer<PagingData<User>>{ _listUser.value = it }

    init {
        getUser()
    }

    fun isPalindrome(str: String): Boolean {
        val input = str.lowercase().replace(" ", "")
        val length = input.length
        for (i in 0 until length / 2) {
            if (input[i] != input[length - i - 1]) {
                return false
            }
        }
        return true
    }

    fun getUser(){
        repository.getUsers().cachedIn(viewModelScope).observeForever(observer)
    }

    override fun onCleared() {
        repository.getUsers().removeObserver(observer)
        super.onCleared()
    }

}