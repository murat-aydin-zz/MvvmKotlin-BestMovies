package com.murat.movielist.ui.main.main

import androidx.lifecycle.Observer
import android.content.Intent
import android.content.res.Configuration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.util.Log
import com.murat.movielist.BuildConfig
import com.murat.movielist.core.BaseActivity
import com.murat.movielist.core.Constants.NetworkService.NOW_PLAYING_TASK
import com.murat.movielist.core.Constants.NetworkService.POPULAR_TASK
import com.murat.movielist.core.Constants.NetworkService.SEARCH_TASK
import com.murat.movielist.core.Constants.NetworkService.TOP_RATED_TASK
import com.murat.movielist.core.Constants.NetworkService.UPCOMING_TASK
import com.murat.movielist.core.Resource
import com.murat.movielist.databinding.ActivityMainBinding
import com.murat.movielist.service.response.Movie
import com.murat.movielist.service.response.TMDBResponse
import com.murat.movielist.ui.main.details.DetailsActivity
import kotlinx.android.synthetic.main.activity_main.*
import com.murat.movielist.R
import com.murat.movielist.core.Constants.NetworkService.FAVORITE_TASK
import com.murat.movielist.db.entitiy.MovieEntity
import com.murat.movielist.ui.main.adapters.MovieAdapter
import com.murat.movielist.utils.PaginationScrollListener


class MainActivity : BaseActivity<MainActivityViewModel, ActivityMainBinding>(
    MainActivityViewModel::class.java) {
    var page =1
    var activeTab: Int = 1
    var data: TMDBResponse? = null
    var movie: ArrayList<Movie>? = null
    var movieEntity: ArrayList<MovieEntity>? = null
    var isLastPage: Boolean = false
    var isLoading: Boolean = false

    override fun getLayoutRes() = R.layout.activity_main


    override fun initViewModel(viewModel: MainActivityViewModel) {
        binding.viewModel = viewModel
    }


    override fun init() {
        super.init()

        getMovies(activeTab, "",page)

        bottom_navigation.setOnNavigationItemSelectedListener { item ->

            when (item.itemId) {
                R.id.action_popular -> {
                    binding.rvMovies.smoothScrollToPosition(0)
                    page=1
                    getMovies(POPULAR_TASK, "",page)
                    activeTab = POPULAR_TASK
                }
                R.id.action_rated -> {
                    binding.rvMovies.smoothScrollToPosition(0)
                    page=1
                    getMovies(TOP_RATED_TASK, "",page)
                    activeTab = TOP_RATED_TASK
                }
                R.id.action_upcoming -> {
                    binding.rvMovies.smoothScrollToPosition(0)
                    page=1
                    getMovies(UPCOMING_TASK, "",page)
                    activeTab = UPCOMING_TASK
                }
                R.id.action_now -> {
                    binding.rvMovies.smoothScrollToPosition(0)
                    page=1
                    getMovies(NOW_PLAYING_TASK, "",page)
                    activeTab = NOW_PLAYING_TASK
                }
                R.id.action_favorites -> {
                    binding.rvMovies.smoothScrollToPosition(0)
                    page=1
                    getMovies(FAVORITE_TASK, "",page)
                    activeTab = FAVORITE_TASK

                }
                else -> getMovies(POPULAR_TASK, "",page)

            }
            true
        }

        search_view.setOnQueryChangeListener { _, newQuery ->
            rv_movies!!.smoothScrollToPosition(0)
            getMovies(SEARCH_TASK, newQuery,page)
            //  searchView.clearQuery();
        }

    }
    private fun fetchFavs() {
        runOnUiThread {
            val adapter = MovieAdapter { item, position ->
                val intent = Intent(application, DetailsActivity::class.java)
                intent.putExtra("movie", item)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                application.startActivity(intent)

            }
            var columns = 2
            if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE)
                columns = 4

            binding.rvMovies.layoutManager = androidx.recyclerview.widget.GridLayoutManager(
                applicationContext,
                columns,
                androidx.recyclerview.widget.GridLayoutManager.VERTICAL,
                false
            )
            binding.rvMovies.adapter = adapter

            if (viewModel.db.movieDao().getMovies().hasActiveObservers())
                 viewModel.db.movieDao().getMovies().removeObservers(this@MainActivity)


            viewModel.db.movieDao().getMovies().observe(
                this@MainActivity,
                Observer<List<MovieEntity>> { t ->
                    (binding.rvMovies.adapter as MovieAdapter).submitList(t as List<MovieEntity?>?)
                }
            )
        }
    }

    private fun getMovies(taskId: Int?, taskQuery: String,page: Int) {
        when (taskId) {
            SEARCH_TASK -> viewModel.searchMovies(BuildConfig.API_TOKEN,"tr",page,taskQuery)
            POPULAR_TASK -> viewModel.getMovies("popular", BuildConfig.API_TOKEN,"tr",page)
            TOP_RATED_TASK -> viewModel.getMovies("top_rated", BuildConfig.API_TOKEN,"tr",page)
            UPCOMING_TASK -> viewModel.getMovies("upcoming", BuildConfig.API_TOKEN,"tr",page)
            NOW_PLAYING_TASK -> viewModel.getMovies("now_playing", BuildConfig.API_TOKEN,"tr",page)
            FAVORITE_TASK -> fetchFavs()
            else -> viewModel.getMovies("popular", BuildConfig.API_TOKEN,"tr",page)
        }
        if (viewModel.getMoviesLiveData.hasActiveObservers())
            viewModel.getMoviesLiveData.removeObservers(this)

        val adapter =
            MovieAdapter { item, position ->
                val intent = Intent(application, DetailsActivity::class.java)
                intent.putExtra("movie", item)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                application.startActivity(intent)

            }
        var columns = 2
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE)
            columns = 4
        val layoutManager = androidx.recyclerview.widget.GridLayoutManager(
            applicationContext,
            columns,
            androidx.recyclerview.widget.GridLayoutManager.VERTICAL,
            false
        )

        binding.rvMovies.layoutManager = layoutManager
        binding.rvMovies.adapter = adapter

        viewModel.getMoviesLiveData.observe(this@MainActivity, Observer<Resource<TMDBResponse>> {
            it.let {
                movieEntity = it?.data?.results
                (binding.rvMovies.adapter as MovieAdapter).submitList(movieEntity as List<MovieEntity?>?)
            }
        })

        /*binding.rvMovies.addOnScrollListener(object : PaginationScrollListener(layoutManager) {
            override fun isLastPage(): Boolean {
                return isLastPage
            }

            override fun isLoading(): Boolean {
                return isLoading
            }

            override fun loadMoreItems() {
                if (movieEntity?.size != layoutManager.itemCount)
                  page.plus(page)
                if(activeTab != FAVORITE_TASK)
                  getMovies(activeTab, "", page.inc())
            }
        })*/
    }

    override fun onBackPressed() {
        if (search_view!!.isSearchBarFocused)
            search_view!!.clearQuery()
        else
            super.onBackPressed()
    }

    override fun onResume() {
        super.onResume()
        getMovies(activeTab, "",page)

        if (viewModel.getMoviesLiveData.hasActiveObservers())
            viewModel.getMoviesLiveData.removeObservers(this)

        viewModel.getMoviesLiveData.observe(this@MainActivity, Observer<Resource<TMDBResponse>> {
            it.let {
                movieEntity = it?.data?.results
                (binding.rvMovies.adapter as MovieAdapter).submitList(movieEntity as List<MovieEntity?>?)
            }
        })
    }


}
