package com.k4tr1n4.marvelcomics.core.network.interceptor

import android.util.Log
import com.k4tr1n4.marvelcomics.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

internal class HttpRequestInterceptor : Interceptor {

  override fun intercept(chain: Interceptor.Chain): Response {
    val originalRequest = chain.request()
    val request = originalRequest
      .newBuilder()
      .url(originalRequest.url.newBuilder()
        .addQueryParameter(TS, BuildConfig.TS)
        .addQueryParameter(API_KEY, BuildConfig.API_KEY)
        .addQueryParameter(HASH, BuildConfig.HASH)
        .build()
      ).build()
    Log.d("Request - url:", request.url.toString())
    Log.d("Request - header:", request.headers.toString())
    Log.d("Request - body:", request.body.toString())
    return chain.proceed(request)
  }
  companion object {
    private const val TS = "ts"
    private const val API_KEY = "apikey"
    private const val HASH = "hash"
  }
}
