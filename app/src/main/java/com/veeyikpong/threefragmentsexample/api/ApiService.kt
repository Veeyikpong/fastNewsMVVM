package com.veeyikpong.threefragmentsexample.api

import com.pongstudio.eathere.api.response.GetNearbyPlacesResponse
import com.pongstudio.eathere.api.response.GetNearbyRestaurantResponse
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query


interface ApiService{
    @GET("maps/api/place/nearbysearch/json")
    fun getNearbyPlaces(@Query("key") apiKey: String,
                        @Query("location") location: String,
                        @Query("radius") radius: Int,
                        @Query("type") type: String,
                        @Query("opennow") opennow: Boolean,
                        @Query("maxprice") maxPrice : Int = 4,
                        @Query("minprice") minPrice : Int = 0): Observable<GetNearbyPlacesResponse>

    @GET("maps/api/place/photo")
    fun getPhotos(@Query("key") apiKey: String,
                  @Query("photoreference") photoReference: String,
                  @Query("maxwidth") maxWidth: Int,
                  @Query("maxheight") maxHeight: Int): Call<String>

    @GET("search")
    fun getNearbyRestaurants(@Header("user-key") apiKey: String,
                             @Query("lat") latitude:Double,
                             @Query("lon") longitude:Double,
                             @Query("start") start:Int,
                             @Query("radius") radius:Int):Observable<GetNearbyRestaurantResponse>

    companion object {
        fun create():ApiService{
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                //.baseUrl("https://maps.googleapis.com/")
                .baseUrl("https://developers.zomato.com/api/v2.1/")
                .client(ApiClient.client)
                .build()

            return retrofit.create(ApiService::class.java)
        }

        fun create_noGson():ApiService{
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .baseUrl("https://maps.googleapis.com/")
                .client(ApiClient.client)
                .build()

            return retrofit.create(ApiService::class.java)
        }
    }
}