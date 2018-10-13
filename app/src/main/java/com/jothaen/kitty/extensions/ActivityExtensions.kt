package com.jothaen.kitty.extensions

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity

fun AppCompatActivity.changeFragment(fragment: Fragment, containerId: Int) {
    supportFragmentManager
            .beginTransaction()
            .replace(containerId, fragment)
            .commit()
}
