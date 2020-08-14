package io.reisonic.devs.View.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reisonic.devs.Model.AdapterData.CardData
import io.reisonic.devs.Model.Variables.Var
import com.example.test.R
import io.reisonic.devs.View.Adapter.RVAdapter
import java.util.*

class FragmentMain:Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment, null)
        return root
    }
}