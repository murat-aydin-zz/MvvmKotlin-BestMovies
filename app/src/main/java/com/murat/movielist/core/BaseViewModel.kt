package com.murat.movielist.core

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import com.murat.movielist.db.AppDatabase
import com.murat.movielist.service.TmdbAPI
import javax.inject.Inject

open class BaseViewModel(app: Application) : RxAwareViewModel(app) {

    var progressLiveData = MutableLiveData<Boolean>()
    var added = MutableLiveData<Boolean>()

    @Inject
    lateinit var baseApi: TmdbAPI

    @Inject
    lateinit var db: AppDatabase
}

