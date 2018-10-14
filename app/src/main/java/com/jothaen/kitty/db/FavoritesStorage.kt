package com.jothaen.kitty.db

import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jothaen.kitty.data.local.KittyImage

interface FavoritesStorage {
    fun put(kitty: KittyImage)
    fun delete(id: String)
    fun getAll(): List<KittyImage>
    fun exists(id: String): Boolean
    fun isEmpty(): Boolean
}

class SharedPrefsFavoritesStorage(private val sharedPreferences: SharedPreferences,
                                  private val gson: Gson) : FavoritesStorage {

    override fun put(kitty: KittyImage) {
        sharedPreferences
                .edit()
                .putString(kitty.id, gson.toJson(kitty))
                .apply()
    }

    override fun delete(id: String) = sharedPreferences.edit().remove(id).apply()

    override fun getAll(): List<KittyImage> {
        val favorites = mutableListOf<KittyImage>()
        sharedPreferences.all.values
                .forEach {
                    if (it is String) favorites.add(gson.fromJson(it, KittyImage::class.java))
                }
        return favorites
    }

    override fun exists(id: String): Boolean = sharedPreferences.contains(id)

    override fun isEmpty(): Boolean = getAll().isEmpty()
}