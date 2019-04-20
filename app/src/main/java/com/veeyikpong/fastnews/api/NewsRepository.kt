package com.veeyikpong.fastnews.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.veeyikpong.fastnews.BuildConfig
import com.veeyikpong.fastnews.api.response.SearchNewsResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import org.koin.core.KoinComponent
import org.koin.core.inject

class NewsRepository : KoinComponent {
    private val apiService: ApiService by inject()

    fun getNews(liveData: MutableLiveData<Resource<SearchNewsResponse>>,query: String){

        liveData.value = Resource.loading()

        apiService.searchNews(query, BuildConfig.API_KEY)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<SearchNewsResponse>() {
                override fun onComplete() {
                }

                override fun onNext(response: SearchNewsResponse?) {
                    if (response!!.status.toLowerCase() != "ok") {
                        liveData.value = Resource.error(Exception())
                        return
                    }

                    liveData.value = Resource.success(response)
                }

                override fun onError(e: Throwable?) {
                    liveData.value = Resource.error(e!!.message)
                }
            })
    }
}