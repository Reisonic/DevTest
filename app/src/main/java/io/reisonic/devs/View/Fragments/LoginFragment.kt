package io.reisonic.devs.View.Fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.test.R
import com.example.test.databinding.LoginfragmentBinding
import io.reisonic.devs.Model.Database.UserDatabase
import io.reisonic.devs.Presenter.Interfaces.LoginInterface
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginFragment:Fragment(),LoginInterface {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: LoginfragmentBinding = DataBindingUtil.inflate(
            inflater, R.layout.loginfragment, container, false)
        val db = UserDatabase(requireContext().applicationContext)
        GlobalScope.launch {
            getDataDatabase(db, requireContext().applicationContext, binding.login, binding.password)
        }
        Glide.with(this).load(R.drawable.logo_group).into(binding.image)
        binding.signUp.setOnClickListener {
            getPosts(binding.login, binding.password, this,requireContext().applicationContext,db)
        }
        return binding.root
    }
}