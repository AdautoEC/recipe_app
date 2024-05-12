package com.k4tr1n4.marvelcomics.comics.di

import com.k4tr1n4.marvelcomics.comics.data.remote.MLService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object ServiceModule {
  @Provides
  @Singleton
  fun provideMLService(retrofit: Retrofit): MLService {
    return retrofit.create(MLService::class.java)
  }
}
