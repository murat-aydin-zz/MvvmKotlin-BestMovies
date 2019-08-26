package com.murat.movielist.ui.main.splash

import android.app.Application
import com.murat.movielist.MyApplication
import com.murat.movielist.core.BaseViewModel

class SplashActivityViewModel(app: Application) : BaseViewModel(app) {


    init {
        (app as? MyApplication)?.component?.inject(this)
    }


}