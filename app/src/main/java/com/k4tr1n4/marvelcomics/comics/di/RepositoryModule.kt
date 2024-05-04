package com.k4tr1n4.marvelcomics.comics.di

import com.k4tr1n4.marvelcomics.comics.data.local.ComicsDao
import com.k4tr1n4.marvelcomics.comics.data.remote.ComicDataStore
import com.k4tr1n4.marvelcomics.comics.data.repository.ComicsRepositoryImpl
import com.k4tr1n4.marvelcomics.comics.domain.repository.ComicsRepository
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
  fun provideRecipeRepository(dataStore: ComicDataStore, dao: ComicsDao) : ComicsRepository =
    ComicsRepositoryImpl(dataStore, dao)
}
