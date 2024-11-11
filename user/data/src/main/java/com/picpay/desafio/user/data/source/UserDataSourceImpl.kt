package com.picpay.desafio.user.data.source

import android.util.Log
import com.picpay.desafio.user.data.api.PicPayService
import com.picpay.desafio.user.data.model.UserResponse
import retrofit2.HttpException

class UserDataSourceImpl(
    private val service: PicPayService
): IUserDataSource {

    private var cachedUsers: List<UserResponse>? = null

    override suspend fun getUsers(): List<UserResponse> {

        cachedUsers?.let {
            return it
        }

        val response = service.getUsers()
        if (response.isSuccessful) {
            val users =  response.body() ?: throw HttpException(response)
            cachedUsers = users
            return users
        } else {
            Log.d(TAG, "getUsers: ${response.errorBody()}")
            throw HttpException(response)
        }
    }

    override fun clearCacheUsers() {
        cachedUsers = null
    }

    companion object {
        private const val TAG = "UserDataSourceImpl"
    }

}