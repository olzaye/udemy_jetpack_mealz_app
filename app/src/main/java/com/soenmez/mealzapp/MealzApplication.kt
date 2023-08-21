package com.soenmez.mealzapp

import android.app.Application
import com.soenmez.mealzapp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

/**
 *
 * @author olcay.soenmez
 * @since 19.08.2023
 */
class MealzApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        //init Timber
        Timber.plant(Timber.DebugTree())

        //init koin
        startKoin { modules(appModule) }
    }
}