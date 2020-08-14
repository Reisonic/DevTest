package io.reisonic.devs.Presenter.NetworkInterface

import io.reisonic.devs.Model.RetrofitData.BoolRequest
import io.reisonic.devs.Model.RetrofitData.Phone
import io.reisonic.devs.Model.RetrofitData.Post
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.QueryMap

interface RetrofitAPI {
    @get:GET("/api/v1/posts")
    val posts: Call<List<Post>>

    @get:GET("/api/v1/phone_masks")
    val phone: Call<Phone>

    @Headers("Content-Type: application/x-www-form-urlencoded")
    @POST("/api/v1/auth")
    fun getPosts(@QueryMap parameters: Map<String, String>): Call<BoolRequest>
}