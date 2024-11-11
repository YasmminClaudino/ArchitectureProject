package com.picpay.desafio.user.data.api

import com.picpay.desafio.user.data.model.UserResponse
import retrofit2.Response
import retrofit2.http.GET

interface PicPayService {

    @GET("users")
        suspend fun getUsers(): Response<List<UserResponse>>
}