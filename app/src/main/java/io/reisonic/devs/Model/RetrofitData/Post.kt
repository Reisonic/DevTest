package io.reisonic.devs.Model.RetrofitData

import com.google.gson.annotations.SerializedName

class Post {
    @SerializedName("id")
    var id: String? = null

    @SerializedName("title")
    var title: String? = null

    @SerializedName("text")
    var text: String? = null

    @SerializedName("image")
    var image: String? = null

    @SerializedName("sort")
    var sort = 0

    @SerializedName("date")
    var date: String? = null

}