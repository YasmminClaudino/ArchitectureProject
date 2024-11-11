package com.picpay.desafio.user.data

import com.nhaarman.mockitokotlin2.whenever
import com.picpay.desafio.user.data.api.PicPayService
import com.picpay.desafio.user.data.di.DataModuleLoaderTest
import com.picpay.desafio.user.data.model.UserResponse
import com.picpay.desafio.user.data.repository.IUserRepository
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import retrofit2.HttpException
import retrofit2.Response
import kotlin.test.assertFailsWith

class UserRepositoryTest: KoinTest {

    private val userRepository: IUserRepository by inject()
    private val picPayService: PicPayService by inject()

    @Before
    fun setup() {
        startKoin {
            val moduleLoader = listOf(
                DataModuleLoaderTest
            )
            moduleLoader.forEach {
                it.loadKoinModule()
            }
        }
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `getUsers search on server on success`() = runTest {
        val networkUsers = listOf(
            UserResponse("https://randomuser.me/api/portraits/women/21.jpg", "Sandrine Spinka", 1, "tod86"),
            UserResponse("https://randomuser.me/api/portraits/women/21.jpg", "Sandrine Spinka", 2, "tod6456"),
            )

        `when`(picPayService.getUsers()).thenReturn(Response.success(networkUsers))

        val users = userRepository.getUsers()

        Assert.assertEquals(networkUsers, users)
        verify(picPayService).getUsers()
    }

    @Test
    fun `getUsers search on server on failure`() = runTest {
        whenever(picPayService.getUsers()).thenReturn(Response.error(404,
            okhttp3.ResponseBody.create(null, "")))

        assertFailsWith<HttpException> {
            userRepository.getUsers()
        }
        verify(picPayService).getUsers()
    }
}
