package com.murat.movielist.di.component

import android.content.Context
import android.content.SharedPreferences
import com.murat.movielist.MyApplication
import com.murat.movielist.di.module.ApplicationModule
import com.murat.movielist.di.module.DatabaseModule
import com.murat.movielist.di.module.NetModule
import com.murat.movielist.ui.main.main.MainActivityViewModel
import com.murat.movielist.ui.main.details.DetailsActivityViewModel
import com.murat.movielist.ui.main.persondetail.PersonActivityViewModel
import com.murat.movielist.ui.main.splash.SplashActivityViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton

@Component(modules = arrayOf(ApplicationModule::class, NetModule::class, DatabaseModule::class))

interface ApplicationComponent {
    fun app(): MyApplication
    fun context(): Context
    fun preferences(): SharedPreferences
    fun inject(mainActivityViewModel: MainActivityViewModel)
    fun inject(detailsActivityViewModel: DetailsActivityViewModel)
    fun inject(splashActivityViewModel: SplashActivityViewModel)
    fun inject(personActivityViewModel: PersonActivityViewModel)
}
