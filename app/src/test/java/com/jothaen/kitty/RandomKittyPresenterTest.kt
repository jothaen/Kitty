package com.jothaen.kitty

import com.jothaen.kitty.data.local.KittyImage
import com.jothaen.kitty.db.FavoritesStorage
import com.jothaen.kitty.ui.random.RandomKittyContract
import com.jothaen.kitty.ui.random.RandomKittyPresenter
import com.jothaen.kitty.usecase.GetRandomCatUseCase
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations


class RandomKittyPresenterTest {

    @Mock
    lateinit var viewMock: RandomKittyContract.View

    @Mock
    lateinit var getRandomCatUseCaseMock: GetRandomCatUseCase

    @Mock
    lateinit var favoritesStorage: FavoritesStorage

    private lateinit var presenter: RandomKittyContract.Presenter

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        presenter = RandomKittyPresenter(getRandomCatUseCaseMock, favoritesStorage).apply { bind(viewMock) }
    }

    @Test
    fun `random cat should be displayed at start`() {
        `when`(getRandomCatUseCaseMock.get()).thenReturn(Observable.just(KittyImage("mockId", "mockUrl", false)))
        presenter.onStart()
        verify(viewMock).showProgress()
        verify(viewMock).hideProgress()
        verify(viewMock).displayKittyImage("mockUrl")
        verify(viewMock).setHeartButtonState(false)
    }

    @Test
    fun `click on get next kitty button should display new random cat`() {
        `when`(getRandomCatUseCaseMock.get()).thenReturn(Observable.just(KittyImage("mockId", "mockUrl", false)))
        presenter.onGetRandomKittyClicked()
        verify(viewMock).showProgress()
        verify(viewMock).hideProgress()
        verify(viewMock).displayKittyImage("mockUrl")
        verify(viewMock).setHeartButtonState(false)
    }

    @Test
    fun `api call error should trigger displaying error message`() {
        `when`(getRandomCatUseCaseMock.get()).thenReturn(Observable.error(Exception()))
        presenter.onGetRandomKittyClicked()
        verify(viewMock).showProgress()
        verify(viewMock).hideProgress()
        verify(viewMock).showError()
    }

}