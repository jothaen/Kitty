package com.jothaen.kitty.ui.favorites

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.jothaen.kitty.R
import com.jothaen.kitty.data.local.KittyImage
import com.jothaen.kitty.extensions.show
import kotlinx.android.synthetic.main.fragment_favorites.*
import org.koin.android.ext.android.inject


class FavoritesFragment : Fragment(), FavoritesContract.View {

    private val presenter: FavoritesContract.Presenter by inject()

    private lateinit var favoritesAdapter: FavoritesAdapter

    companion object {
        fun newInstance() = FavoritesFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_favorites, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.bind(this)
        presenter.onStart()
    }

    override fun showList(favorites: List<KittyImage>) = with(favoritesRecyclerView) {
        favoritesAdapter = FavoritesAdapter(favorites) { id ->
            presenter.onRemoveClicked(id)
        }
        adapter = favoritesAdapter
        layoutManager = LinearLayoutManager(context)
    }

    override fun showEmptyListPlaceholder() = emptyListPlaceholderTextView.show()

    override fun removeFromList(id: String) = favoritesAdapter.removeItem(id)

    override fun onDestroyView() {
        presenter.unbind()
        super.onDestroyView()
    }

}
