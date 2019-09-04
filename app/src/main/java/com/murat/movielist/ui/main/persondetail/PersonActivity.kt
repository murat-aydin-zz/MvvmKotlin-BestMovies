package com.murat.movielist.ui.main.persondetail

import android.os.Build
import android.transition.Slide
import android.view.Gravity
import com.murat.movielist.BuildConfig
import com.murat.movielist.R
import com.murat.movielist.core.BaseActivity

import com.murat.movielist.databinding.ActivityPersonBinding
import kotlinx.android.synthetic.main.activity_details.*


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
