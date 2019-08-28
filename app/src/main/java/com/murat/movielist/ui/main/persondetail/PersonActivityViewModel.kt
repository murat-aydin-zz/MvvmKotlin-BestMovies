package com.murat.movielist.ui.main.persondetail

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



class PersonActivityViewModel(app: Application) : BaseViewModel(app) {

    init {
        (app as? MyApplication)?.component?.inject(this)
    }
    var item = ObservableField<Person>()

    fun getPersonDetail(person_id: Int, api_key: String) {
        disposable.add(baseApi.getPerson(person_id, api_key)
            .subscribeOn(Schedulers.io())
            .map { Resource.success(it) }
            .onErrorReturn { Resource.error(it) }
            .doOnSubscribe { progressLiveData.postValue(true) }
            .doOnTerminate { progressLiveData.postValue(false) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                when (it?.status) {
                    Status.SUCCESS -> item.set(it.data)
                    Status.LOADING -> ""
                    Status.ERROR -> Timber.e(it.error)
                }
            })
    }

}