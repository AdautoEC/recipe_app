package com.k4tr1n4.marvelcomics.comics.di

import com.k4tr1n4.marvelcomics.comics.data.remote.ComicService
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
  fun provideRecipeService(retrofit: Retrofit): ComicService {
    return retrofit.create(ComicService::class.java)
  }
}
