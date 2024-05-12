package com.k4tr1n4.marvelcomics.comics.di

import com.k4tr1n4.marvelcomics.comics.data.remote.MLDataStore
import com.k4tr1n4.marvelcomics.comics.data.repository.MLRepositoryImpl
import com.k4tr1n4.marvelcomics.comics.domain.repository.MLRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object RepositoryModule {
  @Provides
  @Singleton
  fun provideMLRepository(dataStore: MLDataStore) : MLRepository =
    MLRepositoryImpl(dataStore)
}
