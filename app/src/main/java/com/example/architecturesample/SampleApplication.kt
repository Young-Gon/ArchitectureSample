package com.example.architecturesample

import android.app.Application
import com.example.architecturesample.di.networkModule
import com.example.architecturesample.di.repositoryModule
import com.example.architecturesample.di.roomModule
import com.example.architecturesample.di.viewModelModule
import com.example.architecturesample.util.timber.DebugLogTree
import com.example.architecturesample.util.timber.ReleaseLogTree
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class SampleApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugLogTree())
        }
        else{
            Timber.plant(ReleaseLogTree())
        }

        startKoin {
            androidLogger()
            androidContext(this@SampleApplication)
            modules(listOf(
                networkModule,
                roomModule,
                viewModelModule,
                repositoryModule,
            ))
        }
    }
}