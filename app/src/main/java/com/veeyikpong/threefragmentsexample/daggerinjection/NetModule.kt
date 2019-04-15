package com.veeyikpong.threefragmentsexample.daggerinjection

import com.veeyikpong.threefragmentsexample.api.ApiService
import org.koin.dsl.module

private val apiServiceProvider = ApiService.create()
val netModule = module {
    single{ apiServiceProvider}
}