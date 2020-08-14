package io.reisonic.devs.Model.AdapterData

import io.reisonic.devs.Model.Variables.Var
import io.reisonic.devs.Presenter.Interfaces.MainInterface
import java.util.*

class ContentProvider: MainInterface {

    private val items:List<CardData> = ArrayList()

    fun generate(): List<CardData> {
        getData(items)
        val rand = Random(System.currentTimeMillis())
        val list:List<CardData>
        return if (Var.switch_line == 0){
            list = items.sortedBy { it.id }
            list.filter { rand.nextBoolean() }
        } else{
            list = items.sortedBy { it.date }
            list.filter { rand.nextBoolean() }
        }
    }
}