package com.example.newsjam_android.data.di

import com.example.newsjam_android.data.network.NiaDispatchers
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val niaDispatcher: NiaDispatchers)

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class AppCoroutineScope
