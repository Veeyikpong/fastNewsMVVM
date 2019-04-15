package com.veeyikpong.threefragmentsexample.api.response

import com.google.gson.annotations.SerializedName

class Article{

    @SerializedName("id")
    var id: Int = -1

    @SerializedName("title")
    lateinit var title: String

    @SerializedName("body")
    lateinit var body: String
}