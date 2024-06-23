package com.vnteam.architecturetemplates.di_android

import com.vnteam.architecturetemplates.data.database.DatabaseDriverFactory
import com.vnteam.architecturetemplates.data.local.PreferencesFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val androidModule = module {
    single {
        DatabaseDriverFactory(androidContext())
    }
    single {
        PreferencesFactory(androidContext())
    }
}