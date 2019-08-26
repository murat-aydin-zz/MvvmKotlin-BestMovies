package com.murat.movielist.ui.main.details

import android.app.Application
import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableField
import android.databinding.adapters.NumberPickerBindingAdapter.setValue
import android.widget.ImageView
import com.murat.movielist.MyApplication
import com.murat.movielist.core.*
import com.murat.movielist.service.response.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import android.widget.ImageView.ScaleType
import android.widget.LinearLayout



class DetailsActivityViewModel(app: Application) : BaseViewModel(app) {

    val getMoreDetailData = MutableLiveData<Resource<TMDBDetailsResponse>>()

    val getCreditsData = MutableLiveData<Resource<TMDBCreditsResponse>>()

    val getTrailerData = MutableLiveData<Resource<TMDBTrailerResponse>>()

    var favSuccess = MutableLiveData<Boolean>().apply { setValue(false) }

    init {
        (app as? MyApplication)?.component?.inject(this)
    }
    var item = ObservableField<Cast>()
    var position = -1

    var itemTrailer = ObservableField<Trailer>()
    var positionTrailer = -1

    fun setTrailerModel(itemTrailer: Trailer, positionTrailer: Int) {
        this.itemTrailer.set(itemTrailer)
        this.position = positionTrailer

    }
    fun setModel(item: Cast, position: Int) {
        this.item.set(item)
        this.position = position

    }
    fun getMoreDetail(movie_id: Int, api_key: String, language: String) {
        disposable.add(baseApi.getDetails(movie_id, api_key, language)
            .subscribeOn(Schedulers.io())
            .map { Resource.success(it) }
            .onErrorReturn { Resource.error(it) }
            .doOnSubscribe { progressLiveData.postValue(true) }
            .doOnTerminate { progressLiveData.postValue(false) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                when (it?.status) {
                    Status.SUCCESS -> getMoreDetailData.postValue(it)
                    Status.LOADING -> ""
                    Status.ERROR -> Timber.e(it.error)
                }
            })
    }
    fun getCredits(movie_id: Int, api_key: String) {
        disposable.add(baseApi.getCredits(movie_id, api_key)
            .subscribeOn(Schedulers.io())
            .map { Resource.success(it) }
            .onErrorReturn { Resource.error(it) }
            .doOnSubscribe { progressLiveData.postValue(true) }
            .doOnTerminate { progressLiveData.postValue(false) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                when (it?.status) {
                    Status.SUCCESS -> getCreditsData.postValue(it)
                    Status.LOADING -> ""
                    Status.ERROR -> Timber.e(it.error)
                }
            })
    }
    fun getTrailers(movie_id: Int, api_key: String, language: String) {
        disposable.add(baseApi.getTrailers(movie_id, api_key,language)
            .subscribeOn(Schedulers.io())
            .map { Resource.success(it) }
            .onErrorReturn { Resource.error(it) }
            .doOnSubscribe { progressLiveData.postValue(true) }
            .doOnTerminate { progressLiveData.postValue(false) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                when (it?.status) {
                    Status.SUCCESS -> getTrailerData.postValue(it)
                    Status.LOADING -> ""
                    Status.ERROR -> Timber.e(it.error)
                }
            })
    }

}