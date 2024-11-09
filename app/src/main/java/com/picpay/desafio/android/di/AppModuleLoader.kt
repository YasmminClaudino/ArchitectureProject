package com.picpay.desafio.android.di

import com.picpay.desafio.android.model.UserViewModel
import com.picpay.desafio.core.loader.ModuleLoader
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

object AppModuleLoader: ModuleLoader {

    private val appModuleLoader = module {
        viewModel { UserViewModel(get()) }

    }

    override fun loadKoinModule() {
        loadKoinModules(appModuleLoader)
    }
}