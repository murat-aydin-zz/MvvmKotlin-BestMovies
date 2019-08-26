package com.murat.movielist.core

interface BaseCallBack<T> {
    fun onSuccess(data: T)
    fun onFail(message: String)
}