package com.murat.movielist.ui.main.persondetail

import android.content.Intent
import android.net.Uri
import android.os.Build
import com.google.android.material.appbar.AppBarLayout
import androidx.recyclerview.widget.GridLayoutManager
import android.transition.Slide
import android.view.Gravity
import android.view.View
import com.murat.movielist.BuildConfig
import com.murat.movielist.R
import com.murat.movielist.core.BaseActivity
import com.murat.movielist.core.Resource
import androidx.lifecycle.Observer

import com.murat.movielist.databinding.ActivityPersonBinding
import com.murat.movielist.service.response.Cast
import com.murat.movielist.service.response.Person
import com.murat.movielist.service.response.TMDBCreditsResponse
import com.murat.movielist.ui.main.adapters.CastRecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_details.*
import kotlin.math.abs


class PersonActivity :  BaseActivity<PersonActivityViewModel, ActivityPersonBinding>(PersonActivityViewModel::class.java) {
    override fun getLayoutRes() = R.layout.activity_person


    override fun initViewModel(viewModel: PersonActivityViewModel) {
        binding.viewModel = viewModel
    }

    override fun init() {
        super.init()
        setAppBar()
        fetchPerson()
    }
    private fun fetchPerson() {
        val personId = intent.getIntExtra("personId",0)

        viewModel.getPersonDetail(personId, BuildConfig.API_TOKEN)

    }
    private fun setAppBar() {
        if (Build.VERSION.SDK_INT >= 21) {
            val slide = Slide(Gravity.BOTTOM)
            window.enterTransition = slide
            postponeEnterTransition()
        }
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        collapsing_toolbar_layout.setExpandedTitleColor(resources.getColor(android.R.color.transparent))
        collapsing_toolbar_layout.setCollapsedTitleTextColor(resources.getColor(android.R.color.white))
    }


}
