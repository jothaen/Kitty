package com.jothaen.kitty.extensions

import android.support.design.widget.BottomNavigationView

fun BottomNavigationView.assignListeners(vararg itemToAction: Pair<Int, () -> Unit>) {
    setOnNavigationItemSelectedListener { clickedItem ->
        itemToAction.forEach { if (it.first == clickedItem.itemId) it.second.invoke() }
        true
    }
}