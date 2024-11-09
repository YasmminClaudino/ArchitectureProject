package com.picpay.desafio.user.data.di

import com.picpay.desafio.core.loader.ModuleLoader
import com.picpay.desafio.user.data.api.PicPayService
import com.picpay.desafio.user.data.repository.IUserRepository
import com.picpay.desafio.user.data.repository.UserRepositoryImpl
import com.picpay.desafio.user.data.source.IUserDataSource
import com.picpay.desafio.user.data.source.UserDataSourceImpl
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module
import retrofit2.Retrofit


object DataModuleLoader: ModuleLoader {

    private val dataModuleLoader = module {
        single<IUserRepository> { UserRepositoryImpl(get()) }
        single<IUserDataSource> { UserDataSourceImpl(get()) }

        single { get<Retrofit>().create(PicPayService::class.java) }

    }

    override fun loadKoinModule() {
        loadKoinModules(dataModuleLoader)
    }
}