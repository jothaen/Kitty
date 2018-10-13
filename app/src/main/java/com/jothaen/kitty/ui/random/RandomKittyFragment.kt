package com.jothaen.kitty.ui.random


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.jothaen.kitty.R


class RandomKittyFragment : Fragment() {

    companion object {
        fun newInstance() = RandomKittyFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
         inflater.inflate(R.layout.fragment_random_kitty, container, false)

}
