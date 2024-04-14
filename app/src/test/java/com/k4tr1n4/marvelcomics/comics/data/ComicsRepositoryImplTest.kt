package com.k4tr1n4.marvelcomics.comics.data

import app.cash.turbine.test
import com.k4tr1n4.marvelcomics.comics.data.remote.ComicDataStore
import com.k4tr1n4.marvelcomics.comics.data.remote.ComicService
import com.k4tr1n4.marvelcomics.comics.data.repository.ComicsRepositoryImpl
import com.k4tr1n4.marvelcomics.comics.util.MainCourotinesRule
import com.k4tr1n4.marvelcomics.comics.util.MockUtil
import com.k4tr1n4.marvelcomics.core.network.model.LoadingEvent
import com.k4tr1n4.marvelcomics.core.network.model.getErrorThrowableOrNull
import com.k4tr1n4.marvelcomics.core.network.model.getSuccessDataOrNull
import com.k4tr1n4.marvelcomics.core.network.model.isLoading
import com.nhaarman.mockitokotlin2.atLeastOnce
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import kotlin.time.DurationUnit
import kotlin.time.toDuration

class ComicsRepositoryImplTest {

    @Mock
    private lateinit var service: ComicService

    private lateinit var repository: ComicsRepositoryImpl

    private lateinit var dataStore: ComicDataStore

    @get:Rule
    val coroutinesRule = MainCourotinesRule()

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        dataStore = ComicDataStore(service)
        repository = ComicsRepositoryImpl(dataStore)
    }

    @Test
    fun `when execute api getComics return mock success`() = runTest {
        val mockData = MockUtil.mockItemModel()

        whenever(dataStore.getComics()).thenReturn(flowOf(LoadingEvent.Success(mockData)))

        repository.getComics().test(2.toDuration(DurationUnit.SECONDS)) {
            val exceptItem = awaitItem().getSuccessDataOrNull()
            assertEquals(exceptItem, MockUtil.mockItemModel())
            awaitComplete()
        }

        verify(service, atLeastOnce()).getComics()
        verifyNoMoreInteractions(service)
    }

    @Test
    fun `when execute api getComics return mock error`() = runTest {
        val mockThrowable = MockUtil.mockThrowable()

        whenever(dataStore.getComics()).thenReturn(flowOf(LoadingEvent.Error(mockThrowable)))

        repository.getComics().test(2.toDuration(DurationUnit.SECONDS)) {
            val exceptItem = awaitItem().getErrorThrowableOrNull()?.message
            assertEquals(exceptItem, mockThrowable.message)
            awaitComplete()
        }

        verify(service, atLeastOnce()).getComics()
        verifyNoMoreInteractions(service)
    }

    @Test
    fun `when execute api getComics return mock loading`() = runTest {
        whenever(dataStore.getComics()).thenReturn(flowOf(LoadingEvent.Loading))

        repository.getComics().test(2.toDuration(DurationUnit.SECONDS)) {
            val exceptItem = awaitItem().isLoading()
            assertEquals(exceptItem, true)
            awaitComplete()
        }

        verify(service, atLeastOnce()).getComics()
        verifyNoMoreInteractions(service)
    }
}