package com.picpay.desafio.user.data.source

import com.picpay.desafio.user.data.model.UserResponse

interface IUserDataSource {
    suspend fun getUsers(): List<UserResponse>
}