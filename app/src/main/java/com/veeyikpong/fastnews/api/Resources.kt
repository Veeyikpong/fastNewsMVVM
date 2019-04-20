package com.veeyikpong.fastnews.api

import java.lang.Exception

class Resource<T> private constructor(val status: Resource.Status, val data: T?, val exception: Exception?, val errorMessage: String?="") {
    enum class Status {
        SUCCESS, ERROR, LOADING
    }
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }
        fun <T> error(exception: Exception?): Resource<T> {
            return Resource(Status.ERROR, null, exception)
        }
        fun <T> error(error: String?): Resource<T> {
            return Resource(Status.ERROR, null, null, error)
        }
        fun <T> loading(): Resource<T> {
            return Resource(Status.LOADING, null, null)
        }
    }
}