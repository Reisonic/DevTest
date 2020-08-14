package io.reisonic.devs.Presenter

import android.content.Context
import android.util.Log
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import io.reisonic.devs.Model.AdapterData.CardData
import io.reisonic.devs.Model.Database.User
import io.reisonic.devs.Model.Database.UserDatabase
import io.reisonic.devs.Model.Database.UserExecutors
import io.reisonic.devs.Model.RetrofitData.BoolRequest
import io.reisonic.devs.Model.RetrofitData.Phone
import io.reisonic.devs.Model.RetrofitData.Post
import io.reisonic.devs.Model.Variables.Var
import io.reisonic.devs.Presenter.Interfaces.LoginInterface
import io.reisonic.devs.Presenter.NetworkInterface.RetrofitAPI
import io.reisonic.devs.View.Fragments.LoginFragmentDirections
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginPresenter():LoginInterface {
    override fun getPosts(
        login: EditText,
        password: EditText,
        fragment: Fragment,
        context: Context,
        database: UserDatabase
    ) {
        val parameters: MutableMap<String, String> = HashMap()
        parameters["phone"] = login.text.toString()
        parameters["password"] = password.text.toString()
        val retrofit = Retrofit.Builder()
            .baseUrl("http://dev-exam.l-tech.ru/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val retrofitAPI: RetrofitAPI = retrofit.create<RetrofitAPI>(
            RetrofitAPI::class.java
        )
        val call: Call<BoolRequest> = retrofitAPI.getPosts(parameters)
        call.enqueue(object : Callback<BoolRequest?> {
            override fun onResponse(
                call: Call<BoolRequest?>,
                response: Response<BoolRequest?>
            ) {
                if (!response.isSuccessful) {
                    Log.i("Code: ", response.code().toString())
                    return
                }
                val posts: BoolRequest? = response.body()
                Log.i("ResponseBool", posts?.isResponse.toString())
                Thread{
                    val users: List<User?>? = database.userDao()?.loadAllUsers()
                    if (posts?.isResponse == false) {
                        if (users?.size != 0){
                            //context.startActivity(Intent(intent))
                            findNavController(fragment).navigate(LoginFragmentDirections.actionLoginFragmentToMainScreenFragment())
                        } else {
                            UserExecutors.instance?.diskIO()?.execute {
                                val user = User(
                                    0,
                                    login.text.toString(),
                                    password.text.toString()
                                )
                                database.userDao()?.insertUser(user)
                            }
                            //context.startActivity(Intent(intent))
                            findNavController(fragment).navigate(LoginFragmentDirections.actionLoginFragmentToMainScreenFragment())
                        }
                    }
                }.start()
            }

            override fun onFailure(
                call: Call<BoolRequest?>,
                t: Throwable
            ) {
                Log.i("Error connection : ", t.message)
            }
        })
    }

    override fun getMask(editText: EditText) {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://dev-exam.l-tech.ru/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val retrofitAPI: RetrofitAPI = retrofit.create<RetrofitAPI>(
            RetrofitAPI::class.java
        )
        val call: Call<Phone> = retrofitAPI.phone
        var posts: Phone? = null
        call.enqueue(object : Callback<Phone?> {
            override fun onResponse(
                call: Call<Phone?>,
                response: Response<Phone?>
            ) {
                if (!response.isSuccessful()) {
                    Log.i("Code: ", response.code().toString())
                    return
                }
                posts = response.body()
                Var.phone_mask = posts?.phoneMask.toString()
                editText.setText(Var.phone_mask)
                Log.i("Phone :", Var.phone_mask)
            }

            override fun onFailure(call: Call<Phone?>, t: Throwable) {
                Log.i("Fail Connection: ", t.message)
            }
        })
    }

    override fun getData(data: List<CardData>) {
        val url = "http://dev-exam.l-tech.ru"
        val retrofit = Retrofit.Builder()
            .baseUrl("http://dev-exam.l-tech.ru/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val retrofitAPI: RetrofitAPI = retrofit.create(
            RetrofitAPI::class.java)
        val call: Call<List<Post>> = retrofitAPI.posts
        call.enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (!response.isSuccessful) {
                    Log.i("Code: ", response.code().toString())
                    return
                }
                val posts: List<Post> = response.body()!!

                var i = 0
                (data as ArrayList<CardData>).clear()
                while (i != posts.size) {
                    (data as ArrayList<CardData>).add(
                        CardData(
                            posts[i].id.toString(),
                            posts[i].title.toString(),
                            posts[i].text.toString(),
                            url + posts[i].image.toString(),
                            posts[i].sort,
                            posts[i].date.toString()
                        )
                    )
                    //Log.i("Post-mes id",posts[i].sort.toString())
                    i++
                }
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                Log.i("Error :", t.message)
            }
        })
    }

    override fun getDataDatabase(
        database: UserDatabase,
        context: Context,
        login: EditText,
        password: EditText
    ) {
        val users: List<User?>? = database.userDao()?.loadAllUsers()
        if (users?.size != 0){
            login.setText(users?.get(0)?._number.toString())
            password.setText(users?.get(0)?._password.toString())
        } else{
            getMask(login)
        }
    }

}