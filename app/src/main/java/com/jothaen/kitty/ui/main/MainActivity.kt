package com.jothaen.kitty.ui.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.jothaen.kitty.R
import com.jothaen.kitty.extensions.assignClickListeners
import com.jothaen.kitty.extensions.changeFragment
import com.jothaen.kitty.ui.favorites.FavoritesFragment
import com.jothaen.kitty.ui.random.RandomKittyFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity(), MainContract.View {

    private val presenter: MainContract.Presenter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.bind(this)
        presenter.onStart()
        setListeners()
    }

    override fun showRandomKittyScreen() = changeFragment(RandomKittyFragment.newInstance(), fragmentContainer.id)

    override fun showFavoritesScreen() = changeFragment(FavoritesFragment.newInstance(), fragmentContainer.id)

    private fun setListeners() = navigationView.assignClickListeners(
            R.id.randomKittyButton to { presenter.onRandomClicked() },
            R.id.favoritesButton to { presenter.onFavoritesClicked() }
    )

    override fun onDestroy() {
        presenter.unbind()
        super.onDestroy()
    }
}
