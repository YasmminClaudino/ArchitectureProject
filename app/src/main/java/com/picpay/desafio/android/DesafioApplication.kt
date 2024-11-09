package com.picpay.desafio.android

import android.app.Application
import com.picpay.desafio.core.network.Network
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class DesafioApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        loadModules()
    }

    private fun loadModules() {
        startKoin {
            androidLogger()
            androidContext(this@DesafioApplication)
            val moduleLoader = listOf(
                Network,
            )
            moduleLoader.forEach {
                it.loadKoinModule()
            }
        }
    }
}