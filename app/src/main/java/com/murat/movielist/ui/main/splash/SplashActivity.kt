package com.murat.movielist.ui.main.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.murat.movielist.R
import com.murat.movielist.core.BaseActivity
import com.murat.movielist.databinding.ActivitySplashBinding
import com.murat.movielist.ui.main.main.MainActivity

class SplashActivity : BaseActivity<SplashActivityViewModel, ActivitySplashBinding>(SplashActivityViewModel::class.java) {
    override fun getLayoutRes() = R.layout.activity_splash

    override fun initViewModel(viewModel: SplashActivityViewModel) {
        binding.viewModel = viewModel
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 2000)

    }
}
