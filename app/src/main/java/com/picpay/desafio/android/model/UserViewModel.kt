package com.picpay.desafio.android.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.user.data.model.UserResponse
import com.picpay.desafio.user.data.repository.IUserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserViewModel(
    private val repository: IUserRepository
): ViewModel() {

    private val _users = MutableLiveData<List<UserResponse>>()
    val users: LiveData<List<UserResponse>> = _users

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    fun fetchUsers() {
        viewModelScope.launch {
            try {
                val userList = repository.getUsers()
                _users.value = userList
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }

}