package com.veeyikpong.threefragmentsexample.api.response

import com.google.gson.annotations.SerializedName

class GetPostsResponse{
    @SerializedName("status")
    lateinit var status: String

    lateinit var todoList: List<Article>
}

