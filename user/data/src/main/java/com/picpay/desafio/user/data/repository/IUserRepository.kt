package com.picpay.desafio.user.data.repository

import com.picpay.desafio.user.data.model.UserResponse

interface IUserRepository {
    suspend fun getUsers(): List<UserResponse>
    fun clearCacheUsers()
}