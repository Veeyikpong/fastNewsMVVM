package com.veeyikpong.threefragmentsexample

import android.app.Application
import com.veeyikpong.threefragmentsexample.daggerinjection.netModule
import org.koin.core.context.startKoin

class MyApplication: Application(){
    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(netModule)
        }
    }
}