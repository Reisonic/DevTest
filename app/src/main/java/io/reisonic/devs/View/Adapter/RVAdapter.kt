package io.reisonic.devs.View.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.reisonic.devs.Model.AdapterData.CardData
import io.reisonic.devs.Model.Variables.Var
import com.example.test.R
import io.reisonic.devs.Presenter.Adapter.AutoUpdatableAdapter
import io.reisonic.devs.View.Fragments.LoginFragmentDirections
import io.reisonic.devs.View.Fragments.MainScreenFragmentDirections
import kotlin.properties.Delegates

class RVAdapter(): RecyclerView.Adapter<RVAdapter.CardViewHolder>(),
    AutoUpdatableAdapter {

    lateinit var context:Context

    var items: List<CardData> by Delegates.observable(emptyList()) {
            prop, old, new ->
        autoNotify(old, new) { o, n -> o.id == n.id }
    }

    class CardViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var cv: CardView
        var name: TextView
        var photo: ImageView
        var mini_title: TextView
        var date:TextView
        var btn:Button
        var count:Int

        init {
            cv = itemView.findViewById<View>(R.id.cv) as CardView
            name = itemView.findViewById<View>(R.id.title) as TextView
            photo = itemView.findViewById<View>(R.id.imgglide) as ImageView
            mini_title = itemView.findViewById<View>(R.id.sub_title) as TextView
            btn = itemView.findViewById<View>(R.id.button3) as Button
            date = itemView.findViewById<View>(R.id.textView5) as TextView
            count = 1
        }
    }


    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): CardViewHolder {
        val v: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.cardtest, viewGroup, false)
        context = viewGroup.context
        return CardViewHolder(v)
    }

    override fun onBindViewHolder(cardViewHolder: CardViewHolder, i: Int) {
        cardViewHolder.name.text = items[i].title
        Glide.with(cardViewHolder.itemView).load(items[i].image).into(cardViewHolder.photo)
        cardViewHolder.mini_title.text = items[i].text
        val date_one = items[i].date.split("T".toRegex()).map { it.trim() }
        val date_two = date_one[1].split("Z".toRegex()).map { it.trim() }
        cardViewHolder.date.text = date_one[0] + " " + date_two[0]
        cardViewHolder.btn.setOnClickListener(View.OnClickListener {
            //context.startActivity(Intent(context, FinishActivty::class.java))
            //findNavController(fragment).navigate(MainScreenFragmentDirections.actionMainScreenFragmentToDetailedFragment())
            Navigation.createNavigateOnClickListener(
                MainScreenFragmentDirections.actionMainScreenFragmentToDetailedFragment()).onClick(cardViewHolder.itemView)
            Var.title = items[i].title
            Var.detailed_text = items[i].text
            Var.image = items[i].image
        })
    }

    override fun getItemCount(): Int {
        return items.size
    }
}