package com.k4tr1n4.mlteste.products.data

import com.k4tr1n4.mlteste.core.network.model.LoadingEvent
import com.k4tr1n4.mlteste.core.network.model.getErrorThrowableOrNull
import com.k4tr1n4.mlteste.core.network.model.getSuccessDataOrNull
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class LoadingEventTest {

  @Test
  fun exception() {
    val exception = Exception("foo")
    val loadingEvent = LoadingEvent.Error(exception)
    assertThat(loadingEvent.getErrorThrowableOrNull()?.message, `is`("foo"))
  }

  @Test
  fun success() {
    val loadingEvent = LoadingEvent.Success("foo")
    assertThat(loadingEvent.getSuccessDataOrNull(), `is`("foo"))
  }
}
