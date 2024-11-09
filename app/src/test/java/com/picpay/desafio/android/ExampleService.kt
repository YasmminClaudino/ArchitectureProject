package com.picpay.desafio.android

import com.picpay.desafio.user.data.api.PicPayService

class ExampleService(
    private val service: PicPayService
) {

    fun example(): List<com.picpay.desafio.user.detail.data.model.User> {
        val users = service.getUsers().execute()

        return users.body() ?: emptyList()
    }
}