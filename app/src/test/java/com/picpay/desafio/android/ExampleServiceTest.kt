package com.picpay.desafio.android

import com.nhaarman.mockitokotlin2.mock
import com.picpay.desafio.user.data.api.PicPayService

class ExampleServiceTest {

    private val api = mock<PicPayService>()

    private val service = ExampleService(api)

/*    @Test
    fun exampleTest() {
        // given
        val call = mock<Call<User>>()
        val expectedUsers = emptyList<User>()

        whenever(call.execute()).thenReturn(Response.success(expectedUsers))
        whenever(api.getUsers()).thenReturn(call)

        // when
        val users = service.example()

        // then
        assertEquals(users, expectedUsers)
    }*/
}