package io.reisonic.devs.View.Fragments

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test.R
import com.example.test.databinding.MainscreenfragmentBinding
import io.reisonic.devs.Model.AdapterData.ContentProvider
import io.reisonic.devs.Model.Variables.Var
import io.reisonic.devs.Presenter.Interfaces.SecondInterface
import io.reisonic.devs.View.Adapter.RVAdapter

class MainScreenFragment:Fragment(),SecondInterface {

    private lateinit var recyclerView: RecyclerView
    private var timer:Long = 450

    private val provider = ContentProvider()
    private var adapter = RVAdapter()
    val runnable = { fillAdapter(adapter, recyclerView, provider); scheduleReload(timer)}

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: MainscreenfragmentBinding = DataBindingUtil.inflate(inflater, R.layout.mainscreenfragment, container, false)
        recyclerView = binding.root.findViewById(R.id.rv)
        val llm = LinearLayoutManager(requireContext().applicationContext, LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = llm
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter
        runnable()
        timer = 10000
        binding.serverSort.setOnClickListener {
            Var.switch_line = 0
            updateData(Var.switch_line,adapter, recyclerView)
        }
        binding.dateSort.setOnClickListener {
            Var.switch_line = 1
            updateData(Var.switch_line,adapter, recyclerView)
        }
        binding.refreshData.setOnClickListener {
            updateData(adapter, recyclerView, provider)
        }
        return binding.root
    }

    private fun scheduleReload(timer:Long) {
        val handler = Handler()
        handler.postDelayed(runnable, timer)
    }
}