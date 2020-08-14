package io.reisonic.devs.Presenter.Interfaces

import androidx.recyclerview.widget.RecyclerView
import io.reisonic.devs.Model.AdapterData.ContentProvider
import io.reisonic.devs.View.Adapter.RVAdapter
import java.util.logging.Handler

interface SecondInterface {

    fun fillAdapter(adapter:RVAdapter, recyclerView:RecyclerView, provider:ContentProvider) {
        adapter.items = provider.generate()
        recyclerView.scrollToPosition(0)
    }

    fun updateData(switch:Int, adapter:RVAdapter, recyclerView:RecyclerView){
        if (switch == 0){
            adapter.items = adapter.items.sortedBy { it.id }
            recyclerView.scrollToPosition(0)
        } else{
            adapter.items = adapter.items.sortedBy { it.date }
            recyclerView.scrollToPosition(0)
        }
    }

    fun updateData(adapter:RVAdapter, recyclerView:RecyclerView, provider:ContentProvider){
        fillAdapter(adapter, recyclerView, provider)
        recyclerView.scrollToPosition(0)
    }
}