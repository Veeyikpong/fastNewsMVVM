package com.veeyikpong.fastnews

import android.app.Application
import com.veeyikpong.fastnews.daggerinjection.netModule
import org.koin.core.context.startKoin

class MyApplication: Application(){
    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(netModule)
        }
    }
}