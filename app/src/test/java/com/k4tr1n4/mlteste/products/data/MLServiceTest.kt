package com.k4tr1n4.mlteste.products.data

import app.cash.turbine.test
import com.k4tr1n4.mlteste.core.network.model.LoadingEvent
import com.k4tr1n4.mlteste.core.network.model.getErrorThrowableOrNull
import com.k4tr1n4.mlteste.core.network.model.getSuccessDataOrNull
import com.k4tr1n4.mlteste.core.network.model.isError
import com.k4tr1n4.mlteste.core.network.model.isLoading
import com.k4tr1n4.mlteste.products.data.remote.MLService
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.fail
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import java.io.ByteArrayInputStream
import java.io.IOException
import kotlin.time.DurationUnit
import kotlin.time.toDuration

@ExperimentalCoroutinesApi
class MLServiceTest : ApiAbstract<MLService>() {

  private lateinit var service: MLService

  @Before
  fun initService() {
    service = createService(MLService::class.java)
  }

  @Throws(IOException::class)
  @Test
  fun fetchSearchItemFromNetworkTestSuccess() = runTest {
    enqueueResponse("/SearchResponse.json")
    service.getSearchItems().test(2.toDuration(DurationUnit.SECONDS)) {
      if(!awaitItem().isLoading()) fail("Not loading")
      awaitItem().getSuccessDataOrNull()?.let {
        assertThat(it.results[0].id, `is`("MLB3523110833"))
        assertThat(it.results[0].permalink, `is`("https://www.mercadolivre.com.br/bis-chocolate-branco-laka-3024g-tripack-1008g-cada-lacta/p/MLB27989974"))
        assertThat(it.results[0].thumbnail, `is`("http://http2.mlstatic.com/D_664605-MLU75734974743_042024-I.jpg"))
      }?: run { fail("Not return obj") }
      awaitComplete()
    }
  }
}
