package com.k4tr1n4.mlteste.products.di

import com.k4tr1n4.mlteste.products.data.remote.MLDataStore
import com.k4tr1n4.mlteste.products.data.repository.MLRepositoryImpl
import com.k4tr1n4.mlteste.products.domain.repository.MLRepository
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
