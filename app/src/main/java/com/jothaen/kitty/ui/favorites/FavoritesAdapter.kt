package com.jothaen.kitty.ui.favorites

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jothaen.kitty.R
import com.jothaen.kitty.data.local.KittyImage
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_favorite_kitty.view.*


class FavoritesAdapter(items: List<KittyImage>) : RecyclerView.Adapter<FavoritesAdapter.Holder>() {

    private val data = mutableListOf<KittyImage>()

    init {
        data.addAll(items)
    }

    fun removeItem(id: String) {
        var position = -1
        data.forEachIndexed {index, item -> if (item.id == id) position = index}
        data.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesAdapter.Holder =
            Holder(LayoutInflater.from(parent.context).inflate(R.layout.item_favorite_kitty, parent, false))

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: FavoritesAdapter.Holder, position: Int) {
        holder.displayPhoto(data[position].url)
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun displayPhoto(url: String) = Picasso.get().load(url).into(itemView.favoriteImageView)
    }
}