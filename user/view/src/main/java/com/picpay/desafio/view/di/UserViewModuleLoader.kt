package com.picpay.desafio.view.di

import com.picpay.desafio.view.model.UserViewModel
import com.picpay.desafio.core.loader.ModuleLoader
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

object UserViewModuleLoader: ModuleLoader {

    private val userViewModelLoader = module {
        viewModel { UserViewModel(get()) }
    }

    override fun loadKoinModule() {
        loadKoinModules(userViewModelLoader)
    }
}