package com.jothaen.kitty

import com.jothaen.kitty.ui.main.MainContract
import com.jothaen.kitty.ui.main.MainPresenter
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations


class MainPresenterTest {

    @Mock
    lateinit var viewMock: MainContract.View

    private lateinit var presenter: MainContract.Presenter

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        presenter = MainPresenter().apply { bind(viewMock) }
    }

    @Test
    fun `at start random kitty screen should be displayed`() {
        presenter.onStart()
        verify(viewMock).showRandomKittyScreen()
    }

    @Test
    fun `click on random button should open random kitty screen`() {
        presenter.onRandomClicked()
        verify(viewMock).showRandomKittyScreen()
    }

    @Test
    fun `click on favorites button should open favorites screen`() {
        presenter.onFavoritesClicked()
        verify(viewMock).showFavoritesScreen()
    }
}