package com.k4tr1n4.mlteste.products.domain

import app.cash.turbine.test
import com.k4tr1n4.mlteste.products.domain.repository.MLRepository
import com.k4tr1n4.mlteste.products.domain.use_case.GetItemsUseCase
import com.k4tr1n4.mlteste.products.util.MainCourotinesRule
import com.k4tr1n4.mlteste.products.util.MockUtil
import com.k4tr1n4.mlteste.core.network.model.LoadingEvent
import com.k4tr1n4.mlteste.core.network.model.getErrorThrowableOrNull
import com.k4tr1n4.mlteste.core.network.model.getSuccessDataOrNull
import com.k4tr1n4.mlteste.core.network.model.isLoading
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

class GetItemsUseCaseTest {

    @Mock
    private lateinit var repository: MLRepository
    private lateinit var useCase: GetItemsUseCase

    @get:Rule
    val coroutinesRule = MainCourotinesRule()

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        useCase = GetItemsUseCase(repository)
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