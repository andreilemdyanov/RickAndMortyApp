package com.example.rickandmortyapp.presentation.character_list.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickandmortyapp.R
import com.example.rickandmortyapp.data.model.Hero

class HeroesAdapter(private val clickListenerItem: (Hero) -> Unit?) :
    PagingDataAdapter<Hero, HeroViewHolder>(HeroesDiffCallback()) {

    override fun onBindViewHolder(holder: HeroViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener { getItem(position)?.let { hero -> clickListenerItem(hero) } }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return HeroViewHolder(inflater.inflate(R.layout.view_holder_hero, parent, false))
    }

}

class HeroViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val context = itemView.context
    private val avatar = itemView.findViewById<ImageView>(R.id.iv_avatar)
    private val name = itemView.findViewById<TextView>(R.id.tv_name)
    private val status = itemView.findViewById<TextView>(R.id.tv_status)
    private val location = itemView.findViewById<TextView>(R.id.tv_location)
    private val seen = itemView.findViewById<TextView>(R.id.tv_seen)
    private val circle = itemView.findViewById<ImageView>(R.id.iv_circle)


    fun bind(hero: Hero?) {
        hero?.let {
            Glide.with(context)
                .load(it.image)
                .placeholder(R.drawable.place_holder)
                .centerCrop()
                .into(avatar)

            name.text = it.name
            status.text = context.getString(R.string.status, it.status, it.species)
            location.text = it.location.name
            seen.text = it.firstEpisode
//            Log.d("HeroViewHolder", "${it.id} ${it.status}")
            when (it.status.trim()) {
                "Alive" -> circle.setImageDrawable(
                    AppCompatResources.getDrawable(
                        context,
                        R.drawable.alive
                    )
                )
                "Dead" -> circle.setImageDrawable(
                    AppCompatResources.getDrawable(
                        context,
                        R.drawable.dead
                    )
                )
                else -> circle.setImageDrawable(
                    AppCompatResources.getDrawable(
                        context,
                        R.drawable.unknown
                    )
                )
            }
        }
    }
}
