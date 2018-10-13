package com.jothaen.kitty.extensions

import android.support.design.widget.BottomNavigationView
import android.view.View

fun BottomNavigationView.assignListeners(vararg itemToAction: Pair<Int, () -> Unit>) {
    setOnNavigationItemSelectedListener { clickedItem ->
        itemToAction.forEach { if (it.first == clickedItem.itemId) it.second.invoke() }
        true
    }
}

fun assignListeners(vararg viewToAction: Pair<View, () -> Unit>) = viewToAction.forEach { pair ->
    pair.first.setOnClickListener { pair.second.invoke() }
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun showViews(vararg view: View) = view.forEach { it.show() }

fun hideViews(vararg view: View) = view.forEach { it.hide() }
