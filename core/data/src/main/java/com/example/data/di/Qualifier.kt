package com.example.data.di

import com.example.data.network.NiaDispatchers
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val niaDispatcher: NiaDispatchers)

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class AppCoroutineScope


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MainRetrofit
