package io.reisonic.devs.Presenter.Interfaces

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.EditText
import io.reisonic.devs.Model.AdapterData.CardData
import io.reisonic.devs.Model.Database.User
import io.reisonic.devs.Model.Database.UserDatabase
import io.reisonic.devs.Model.Database.UserExecutors.Companion.instance
import io.reisonic.devs.Model.RetrofitData.BoolRequest
import io.reisonic.devs.Model.RetrofitData.Phone
import io.reisonic.devs.Model.RetrofitData.Post
import io.reisonic.devs.Model.Variables.Var
import io.reisonic.devs.Presenter.NetworkInterface.RetrofitAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface MainInterface {

    fun getPosts(login: EditText, password: EditText, intent: Intent, context: Context, database: UserDatabase)

    fun getMask(editText: EditText)

    fun getData(data: List<CardData>)

    fun getDataDatabase(database: UserDatabase, context: Context, login: EditText, password: EditText)
}