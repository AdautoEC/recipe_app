package com.k4tr1n4.marvelcomics.comics.domain

import app.cash.turbine.test
import com.k4tr1n4.marvelcomics.comics.data.remote.ComicDataStore
import com.k4tr1n4.marvelcomics.comics.data.remote.ComicService
import com.k4tr1n4.marvelcomics.comics.data.repository.ComicsRepositoryImpl
import com.k4tr1n4.marvelcomics.comics.domain.repository.ComicsRepository
import com.k4tr1n4.marvelcomics.comics.domain.use_case.GetComicsUseCase
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
import junit.framework.TestCase
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import kotlin.time.DurationUnit
import kotlin.time.toDuration

class GetComicsUseCaseTest {

    @Mock
    private lateinit var repository: ComicsRepository
    private lateinit var useCase: GetComicsUseCase

    @get:Rule
    val coroutinesRule = MainCourotinesRule()

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        useCase = GetComicsUseCase(repository)
    }

    @Test
    fun `when execute api getComics return mock success`() = runTest {
        val mockItem = MockUtil.mockItemModel()

        whenever(repository.getComics()).thenReturn(flowOf(LoadingEvent.Success(mockItem)))

        useCase().test(2.toDuration(DurationUnit.SECONDS)) {
            val exceptItem = awaitItem().getSuccessDataOrNull()?.get(0)
            TestCase.assertEquals(exceptItem, MockUtil.mockComicsModel())
            awaitComplete()
        }

        verify(repository, atLeastOnce()).getComics()
        verifyNoMoreInteractions(repository)
    }

    @Test
    fun `when execute api getComics return mock error`() = runTest {
        val mockThrowable = MockUtil.mockThrowable()

        whenever(repository.getComics()).thenReturn(flowOf(LoadingEvent.Error(mockThrowable)))

        useCase().test(2.toDuration(DurationUnit.SECONDS)) {
            val exceptItem = awaitItem().getErrorThrowableOrNull()?.message
            TestCase.assertEquals(exceptItem, MockUtil.mockThrowable().message)
            awaitComplete()
        }

        verify(repository, atLeastOnce()).getComics()
        verifyNoMoreInteractions(repository)
    }

    @Test
    fun `when execute api getComics return mock loading`() = runTest {

        whenever(repository.getComics()).thenReturn(flowOf(LoadingEvent.Loading))

        useCase().test(2.toDuration(DurationUnit.SECONDS)) {
            val exceptItem = awaitItem().isLoading()
            TestCase.assertEquals(exceptItem, true)
            awaitComplete()
        }

        verify(repository, atLeastOnce()).getComics()
        verifyNoMoreInteractions(repository)
    }
}