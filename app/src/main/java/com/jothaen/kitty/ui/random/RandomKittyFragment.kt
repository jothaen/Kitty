package com.jothaen.kitty.ui.random


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.jothaen.kitty.R
import com.jothaen.kitty.extensions.assignClickListeners
import com.jothaen.kitty.extensions.hide
import com.jothaen.kitty.extensions.show
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_random_kitty.*
import org.koin.android.ext.android.inject


class RandomKittyFragment : Fragment(), RandomKittyContract.View {

    companion object {
        fun newInstance() = RandomKittyFragment()
    }

    private val presenter: RandomKittyContract.Presenter by inject()
    private val picasso: Picasso by inject()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_random_kitty, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.bind(this)
        presenter.onStart()
        initListeners()
    }

    override fun showProgress() = progressView.show()

    override fun hideProgress() = progressView.hide()

    override fun showError() {
        // TODO
    }

    override fun displayKittyImage(url: String) = picasso.load(url).into(kittyImageView)

    private fun initListeners() = assignClickListeners(
            getNextKittyButton to { presenter.onGetRandomKittyClicked() },
            lineUnlikeButton to { /* TODO */ }
    )

    override fun onDestroyView() {
        presenter.unbind()
        super.onDestroyView()
    }

}
