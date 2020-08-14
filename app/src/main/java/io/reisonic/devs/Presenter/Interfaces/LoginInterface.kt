package io.reisonic.devs.Presenter.Interfaces

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.findNavController
import io.reactivex.Observable
import io.reactivex.rxkotlin.Observables
import io.reisonic.devs.Model.AdapterData.CardData
import io.reisonic.devs.Model.Database.User
import io.reisonic.devs.Model.Database.UserDatabase
import io.reisonic.devs.Model.Database.UserExecutors
import io.reisonic.devs.Model.RetrofitData.BoolRequest
import io.reisonic.devs.Model.RetrofitData.Phone
import io.reisonic.devs.Model.RetrofitData.Post
import io.reisonic.devs.Model.Variables.Var
import io.reisonic.devs.Presenter.NetworkInterface.RetrofitAPI
import io.reisonic.devs.View.Fragments.LoginFragmentDirections
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

interface LoginInterface {
    fun getPosts(login: EditText, password: EditText, fragment: Fragment, context: Context, database: UserDatabase)
    fun getMask(editText: EditText)
    fun getData(data: List<CardData>)
    fun getDataDatabase(database: UserDatabase, context: Context, login: EditText, password: EditText)
}