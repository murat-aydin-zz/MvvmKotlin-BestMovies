package com.murat.movielist.ui.main.details

import android.arch.lifecycle.Observer
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.support.design.widget.AppBarLayout
import android.support.v7.widget.GridLayoutManager
import android.transition.Slide
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import com.murat.movielist.BuildConfig
import com.murat.movielist.R
import com.murat.movielist.core.BaseActivity
import com.murat.movielist.core.Constants
import com.murat.movielist.core.Resource
import com.murat.movielist.databinding.ActivityDetailsBinding
import com.murat.movielist.service.response.*
import com.murat.movielist.ui.main.adapters.CastRecyclerViewAdapter
import com.murat.movielist.ui.main.adapters.TrailerRecyclerViewAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_details.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs
import com.murat.movielist.db.entitiy.MovieEntity
import kotlinx.android.synthetic.main.activity_details.poster_image_view
import org.jetbrains.anko.doAsync
import android.webkit.WebView
import android.webkit.WebViewClient
import com.murat.movielist.ui.main.persondetail.PersonActivity
import com.murat.movielist.ui.main.persondetail.PersonActivityViewModel


class DetailsActivity :  BaseActivity<DetailsActivityViewModel, ActivityDetailsBinding>(DetailsActivityViewModel::class.java) {
    override fun getLayoutRes() = R.layout.activity_details
    var isImageFitToScreen = false
    var movie: MovieEntity? = null
    var inserted : MovieEntity? = null
    var cast: ArrayList<Cast>? = null
    var trailer: ArrayList<Trailer>? = null

    override fun initViewModel(viewModel: DetailsActivityViewModel) {
        binding.viewModel = viewModel

        setAppBar()
        fillUI()
        fetchCredits()
        fetchDetails()

    }

    
    private fun fetchDetails() {
        viewModel.getTrailers(movie!!.id, BuildConfig.API_TOKEN,"tr")
        if (viewModel.getTrailerData.hasActiveObservers())
            viewModel.getTrailerData.removeObservers(this)

        viewModel.getTrailerData.observe(this@DetailsActivity, Observer<Resource<TMDBTrailerResponse>> {
            it.let {
                trailer = it?.data?.results
                (binding.rvTrailers.adapter as TrailerRecyclerViewAdapter).submitList(trailer as List<Trailer?>?)

            }
        })
           val adapter =
            TrailerRecyclerViewAdapter { item, position ->
                val youtubeUri = Uri.parse("https://www.youtube.com/embed/${trailer?.get(0)?.key}")
                val openYoutube = Intent(Intent.ACTION_VIEW, youtubeUri)
                startActivity(openYoutube)
            }
        binding.rvTrailers.adapter = adapter
        binding.rvTrailers.layoutManager = GridLayoutManager(applicationContext, 1, GridLayoutManager.HORIZONTAL, false)
    }

    private fun fetchCredits() {
        viewModel.getMoreDetail(movie!!.id, BuildConfig.API_TOKEN,"tr")
        viewModel.getCredits(movie!!.id, BuildConfig.API_TOKEN)
        if (viewModel.getCreditsData.hasActiveObservers())
            viewModel.getCreditsData.removeObservers(this)

        viewModel.getCreditsData.observe(this@DetailsActivity, Observer<Resource<TMDBCreditsResponse>> {
            it.let {
                cast = it?.data?.cast
                for (crew in it?.data?.crew!!) {
                    if (crew.job.equals("Director")) {
                        director_value_tv.text = crew.name
                        break
                    }
                }
               (binding.castRv.adapter as CastRecyclerViewAdapter).submitList(cast as List<Cast?>?)

            }
        })
        val adapter =
            CastRecyclerViewAdapter { item, position ->

                val intent = Intent(application, PersonActivity::class.java)
                intent.putExtra("personId", item.id)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                application.startActivity(intent)
              /*  val uri =
                    Uri.parse("https://www.google.com/search?q=${cast?.get(position)?.name}. movies")
                val actorMoviesIntent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(actorMoviesIntent)*/


            }
        binding.castRv.adapter = adapter
        binding.castRv.layoutManager = GridLayoutManager(applicationContext, 2, GridLayoutManager.HORIZONTAL, false)
    }

    private fun fillUI() {
        rating_value_tv.text = movie?.vote_average
        date_value_tv.text = prettifyDate(movie?.release_date)
        title_tv.text= movie?.title
        plot_tv.text = movie?.overview
        Picasso.get().load(Constants.NetworkService.BACKDROP_BASE_URL + movie?.backdropPath).into(backdrop_iv)
        Picasso.get().load(Constants.NetworkService.POSTER_BASE_URL + movie?.posterPath).into(poster_image_view)
        doAsync {
            inserted = viewModel.db.movieDao().getMovie(movie?.id!!)
            if( inserted !=null ){
               binding.favButton.setImageResource(R.drawable.ic_favorite_black_24dp)
            }
        }
        binding.favButton.setOnClickListener {
            doAsync {
                inserted = viewModel.db.movieDao().getMovie(movie?.id!!)
                if(inserted==null){
                    binding.favButton.setImageResource(R.drawable.ic_favorite_black_24dp)
                    viewModel.db.movieDao().insertMovie(movie!!)

                }else{
                    viewModel.db.movieDao().deleteMovie(movie?.id!!)
                    binding.favButton.setImageResource(R.drawable.ic_favorite_border)
                }
            }
        }
    }

    private fun prettifyDate(jsonDate: String?): String {
        val sourceDateFormat = SimpleDateFormat("yyyy-MM-dd")
        var date: Date? = null
        try {
            date = sourceDateFormat.parse(jsonDate)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        val destDateFormat = SimpleDateFormat("MMM dd\nyyyy")
        return destDateFormat.format(date)
    }

    private fun setAppBar() {
        if (Build.VERSION.SDK_INT >= 21) {
            val slide = Slide(Gravity.BOTTOM)
            window.enterTransition = slide
            postponeEnterTransition()
        }
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        movie = intent.getParcelableExtra("movie")
        collapsing_toolbar_layout.title = movie?.title
        collapsing_toolbar_layout.setExpandedTitleColor(resources.getColor(android.R.color.transparent))
        collapsing_toolbar_layout.setCollapsedTitleTextColor(resources.getColor(android.R.color.white))
        appbar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            if (abs(verticalOffset) - appBarLayout.totalScrollRange == 0)
                poster_image_view.visibility = View.GONE
            else
                poster_image_view.visibility = View.VISIBLE
        })
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
