package com.murat.movielist.di.module

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.murat.movielist.MyApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(var app: MyApplication) {

    @Provides
    @Singleton
    fun provideApp(): MyApplication = app

    @Provides
    @Singleton
    fun provideContext(): Context = app.applicationContext

    @Provides
    @Singleton
    fun provideSharedPreferences(): SharedPreferences = PreferenceManager.getDefaultSharedPreferences(app)
}
