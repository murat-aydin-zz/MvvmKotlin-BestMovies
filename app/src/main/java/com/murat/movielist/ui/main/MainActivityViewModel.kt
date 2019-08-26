package com.murat.movielist.ui.main

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableField
import android.util.Log
import com.murat.movielist.MyApplication
import com.murat.movielist.core.*
import com.murat.movielist.db.entitiy.MovieEntity
import com.murat.movielist.service.response.Movie
import com.murat.movielist.service.response.TMDBResponse
import com.murat.movielist.service.response.Trailer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class MainActivityViewModel(app: Application) : BaseViewModel(app) {

    val getMoviesLiveData = MutableLiveData<Resource<TMDBResponse>>()


    init {
        (app as? MyApplication)?.component?.inject(this)
    }
    var item = ObservableField<MovieEntity>()
    var position = -1

    fun setModel(item: MovieEntity, position: Int) {
        this.item.set(item)
        this.position = position

    }


    fun getMovies(type: String, api_key: String, language: String, page: Int) {
        disposable.add(baseApi.getMovies(type, api_key, language,page)
            .subscribeOn(Schedulers.io())
            .map { Resource.success(it) }
            .onErrorReturn { Resource.error(it) }
            .doOnSubscribe { progressLiveData.postValue(true) }
            .doOnTerminate { progressLiveData.postValue(false) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                when (it?.status) {
                    Status.SUCCESS -> getMoviesLiveData.postValue(it)
                    Status.LOADING -> ""
                    Status.ERROR -> Timber.e(it.error)
                }
            })
    }
    fun searchMovies(api_key: String, language: String, page: Int, query: String) {
        disposable.add(baseApi.searchMovies(api_key, language,page,query)
            .subscribeOn(Schedulers.io())
            .map { Resource.success(it) }
            .onErrorReturn { Resource.error(it) }
            .doOnSubscribe { progressLiveData.postValue(true) }
            .doOnTerminate { progressLiveData.postValue(false) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                when (it?.status) {
                    Status.SUCCESS -> getMoviesLiveData.postValue(it)
                    Status.LOADING -> ""
                    Status.ERROR -> Timber.e(it.error)
                }
            })
    }

}