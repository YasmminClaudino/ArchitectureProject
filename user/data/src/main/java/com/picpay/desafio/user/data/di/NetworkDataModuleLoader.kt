package com.picpay.desafio.user.data.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.picpay.desafio.core.loader.ModuleLoader
import okhttp3.OkHttpClient
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkDataModuleLoader: ModuleLoader {

    private const val URL = "https://609a908e0f5a13001721b74e.mockapi.io/picpay/api/"
    private val gson: Gson =  GsonBuilder().create()
    private val networkModuleLoader = module {

        single {

            val okHttp: OkHttpClient =
                OkHttpClient.Builder()
                    .build()

            Retrofit.Builder()
                .baseUrl(URL)
                .client(okHttp)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }

    }

    override fun loadKoinModule() {
        loadKoinModules(networkModuleLoader)
    }
}