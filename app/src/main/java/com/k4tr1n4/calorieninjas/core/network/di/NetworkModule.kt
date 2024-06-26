package com.k4tr1n4.calorieninjas.core.network.di

import com.k4tr1n4.calorieninjas.core.network.factor.FlowCallAdapterFactory
import com.k4tr1n4.calorieninjas.core.network.interceptor.HttpRequestInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

  @Provides
  @Singleton
  fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
      .addInterceptor(HttpRequestInterceptor())
      .build()
  }

  @Provides
  @Singleton
  fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
      .client(okHttpClient)
      .baseUrl("https://api.edamam.com/")
      .addCallAdapterFactory(FlowCallAdapterFactory.create())
      .addConverterFactory(GsonConverterFactory.create())
      .build()
  }
}
