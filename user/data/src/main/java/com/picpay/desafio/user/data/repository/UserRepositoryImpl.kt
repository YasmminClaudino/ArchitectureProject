package com.picpay.desafio.user.data.repository

import com.picpay.desafio.user.data.model.UserResponse
import com.picpay.desafio.user.data.source.IUserDataSource

class UserRepositoryImpl(
    private val dataSource: IUserDataSource
): IUserRepository  {
    override suspend fun getUsers(): List<UserResponse> {
        return dataSource.getUsers()
    }
}