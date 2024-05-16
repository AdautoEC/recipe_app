package com.k4tr1n4.mlteste.products.data

import app.cash.turbine.test
import com.k4tr1n4.mlteste.core.network.model.LoadingEvent
import com.k4tr1n4.mlteste.core.network.model.getErrorThrowableOrNull
import com.k4tr1n4.mlteste.core.network.model.getSuccessDataOrNull
import com.k4tr1n4.mlteste.core.network.model.isLoading
import com.k4tr1n4.mlteste.products.data.remote.MLDataStore
import com.k4tr1n4.mlteste.products.data.remote.MLService
import com.k4tr1n4.mlteste.products.data.repository.MLRepositoryImpl
import com.k4tr1n4.mlteste.products.util.MainCourotinesRule
import com.k4tr1n4.mlteste.products.util.MockUtil
import com.nhaarman.mockitokotlin2.atLeastOnce
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import kotlin.time.DurationUnit
import kotlin.time.toDuration

class MLRepositoryTest {

    @Mock
    private lateinit var service: MLService

    private lateinit var repository: MLRepositoryImpl

    private lateinit var dataStore: MLDataStore

    @get:Rule
    val coroutinesRule = MainCourotinesRule()

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        dataStore = MLDataStore(service)
        repository = MLRepositoryImpl(dataStore)
    }

    @Test
    fun `when execute api getSearchItems return mock success`() = runTest {
        val mockData = MockUtil.mockSearchItemModel()

        whenever(dataStore.getSearchItems("", 0))
            .thenReturn(flowOf(LoadingEvent.Success(mockData)))

        repository.getSearchItems("", 0 ).test (2.toDuration(DurationUnit.SECONDS)){
            awaitItem().getSuccessDataOrNull()?.let {
                assertEquals(it, MockUtil.mockSearchItemModel())
            }
            awaitComplete()
        }

        verify(service, atLeastOnce()).getSearchItems("", 0)
        verifyNoMoreInteractions(service)
    }

    @Test
    fun `when execute api getSearchItems return mock error`() = runTest {
        val mockThrowable = MockUtil.mockThrowable()

        whenever(dataStore.getSearchItems("", 0))
            .thenReturn(flowOf(LoadingEvent.Error(mockThrowable)))

        repository.getSearchItems("", 0 ).test (2.toDuration(DurationUnit.SECONDS)){
            awaitItem().getErrorThrowableOrNull()?.let {
                assertEquals(it, mockThrowable)
            }
            awaitComplete()
        }

        verify(service, atLeastOnce()).getSearchItems("", 0)
        verifyNoMoreInteractions(service)
    }

    @Test
    fun `when execute api getSearchItems return mock loading`() = runTest {
        whenever(dataStore.getSearchItems("", 0)).thenReturn(flowOf(LoadingEvent.Loading))

        repository.getSearchItems("", 0).test(2.toDuration(DurationUnit.SECONDS)) {
            assertEquals(awaitItem().isLoading(), true)
            awaitComplete()
        }

        verify(service, atLeastOnce()).getSearchItems("", 0)
        verifyNoMoreInteractions(service)
    }
}