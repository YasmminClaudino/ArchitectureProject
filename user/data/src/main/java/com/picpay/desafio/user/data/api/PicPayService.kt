package com.picpay.desafio.user.data.api

import com.picpay.desafio.user.data.model.UserResponse
import retrofit2.Call
import retrofit2.http.GET


interface PicPayService {

    @GET("users")
    fun getUsers(): Call<List<UserResponse>>
}