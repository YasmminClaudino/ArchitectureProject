package com.picpay.desafio.user.data.source

import com.picpay.desafio.user.data.api.PicPayService
import com.picpay.desafio.user.data.model.UserResponse
import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.Response
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class UserDataSourceImpl(
    private val service: PicPayService
): IUserDataSource {

    override suspend fun getUsers(): List<UserResponse> =
        suspendCancellableCoroutine { continuation ->
            val call = service.getUsers()
            continuation.invokeOnCancellation { call.cancel() }

            call.enqueue(object : Callback<List<UserResponse>> {
                override fun onResponse(
                    call: Call<List<UserResponse>>,
                    response: Response<List<UserResponse>>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        continuation.resume(response.body()!!)
                    } else {
                        continuation.resumeWithException(HttpException(response))
                    }
                }

                override fun onFailure(call: Call<List<UserResponse>>, t: Throwable) {
                    if (continuation.isCancelled) return
                    continuation.resumeWithException(t)
                }
            })
        }
}