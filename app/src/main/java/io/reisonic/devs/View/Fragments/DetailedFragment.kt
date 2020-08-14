package io.reisonic.devs.View.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.test.R
import com.example.test.databinding.FinishfragmentBinding
import io.reisonic.devs.Presenter.FinishPresenter
import org.koin.android.ext.android.inject

class DetailedFragment:Fragment() {

    val finishPresenter:FinishPresenter by inject()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding:FinishfragmentBinding= DataBindingUtil.inflate(inflater, R.layout.finishfragment, container, false)
        binding.back.setOnClickListener { findNavController().navigate(DetailedFragmentDirections.actionDetailedFragmentToMainScreenFragment()) }
        finishPresenter.initData(binding.title,binding.subTitle,binding.detailedText,binding.image,requireContext().applicationContext)
        return binding.root
    }
}