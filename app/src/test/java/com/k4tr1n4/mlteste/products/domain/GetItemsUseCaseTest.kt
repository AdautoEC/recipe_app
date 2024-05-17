package com.k4tr1n4.mlteste.products.domain

import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.map
import app.cash.turbine.test
import com.k4tr1n4.mlteste.products.data.remote.model.SearchItemModel
import com.k4tr1n4.mlteste.products.domain.data_source.ItemDataSourceFactory
import com.k4tr1n4.mlteste.products.domain.use_case.GetItemsUseCase
import com.k4tr1n4.mlteste.products.util.MainCourotinesRule
import com.k4tr1n4.mlteste.products.util.MockUtil
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.MockitoAnnotations

class GetItemsUseCaseTest {
    private val dataSourceFactory: ItemDataSourceFactory = mockk()
    private val useCase = GetItemsUseCase(dataSourceFactory)

    @get:Rule
    val coroutinesRule = MainCourotinesRule()

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `when execute data source getItems return mock success`() = runTest {
        //ToDO(pag3 test)
        val mockItem = MockUtil.mockMLList()[0].title
        val expectedFlow = flowOf(
            PagingData.from(MockUtil.mockSearchItemModel().results)
        ).flowOn(coroutinesRule.testDispatcher)
        coEvery { dataSourceFactory.getItems("") } returns expectedFlow

        useCase.invoke("").test {
            val item = awaitItem()
            item.map{
                assertEquals(mockItem, it.title)
            }

            awaitComplete()
        }
    }

    @Test
    fun `when execute data source getItems return mock error`() = runTest {
        //ToDO(pag3 test)
        val expectedFlow = flowOf(PagingData.empty<SearchItemModel.Result>(
            sourceLoadStates = MockUtil.mockLoadStatesError()
        )).flowOn(coroutinesRule.testDispatcher)
        coEvery { dataSourceFactory.getItems("") } returns expectedFlow

        useCase.invoke("").test {
            val item = awaitItem()
            item.map{
                assertEquals(LoadState.Error(MockUtil.mockThrowable()), item)
            }

            awaitComplete()
        }
    }

    @Test
    fun `when execute data source getItems return mock loading`() = runTest {
        //ToDO(pag3 test)
        val expectedFlow = flowOf(PagingData.empty<SearchItemModel.Result>(
            sourceLoadStates = MockUtil.mockLoadStatesLoading()
        )).flowOn(coroutinesRule.testDispatcher)
        coEvery { dataSourceFactory.getItems("") } returns expectedFlow

        useCase.invoke("").test {
            val item = awaitItem()
            item.map{
                assertEquals(LoadState.Loading, item)
            }

            awaitComplete()
        }
    }
}