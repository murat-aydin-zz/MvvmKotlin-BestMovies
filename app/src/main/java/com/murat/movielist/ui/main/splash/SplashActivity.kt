package com.murat.movielist.ui.main.splash

import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.support.v4.content.ContextCompat
import com.mikhaellopez.rxanimation.RxAnimation
import com.mikhaellopez.rxanimation.fadeIn
import com.mikhaellopez.rxanimation.*
import com.murat.movielist.R
import com.murat.movielist.core.BaseActivity
import com.murat.movielist.databinding.ActivitySplashBinding
import com.murat.movielist.ui.main.MainActivity
import com.murat.movielist.utils.extensions.pixelsToDps
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : BaseActivity<SplashActivityViewModel, ActivitySplashBinding>(SplashActivityViewModel::class.java) {
    override fun getLayoutRes() = R.layout.activity_splash

    override fun initViewModel(viewModel: SplashActivityViewModel) {
        binding.viewModel = viewModel
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        RxAnimation.from(circularImageView)
            .fadeIn()
            .shake()
            .fadeOut()
            .subscribe()

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
