package com.murat.movielist.core

import io.reactivex.annotations.NonNull

class Resource<T> constructor(val status: Status, val data: T?, val error: Throwable? = null) {

    companion object {

        fun <T> success(@NonNull data: T): Resource<T> {
            return Resource(Status.SUCCESS, data)
        }

        fun <T> error(throwable: Throwable): Resource<T> {
            return Resource(status = Status.ERROR, data = null, error = throwable)
        }

        fun <T> loading(): Resource<T> = Resource(Status.LOADING, null)
    }
}