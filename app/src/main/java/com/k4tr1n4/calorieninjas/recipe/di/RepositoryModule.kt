package com.k4tr1n4.calorieninjas.recipe.di

import com.k4tr1n4.calorieninjas.recipe.data.remote.RecipeDataStore
import com.k4tr1n4.calorieninjas.recipe.data.repository.RecipeRepositoryImpl
import com.k4tr1n4.calorieninjas.recipe.domain.repository.RecipeRepository
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
  fun provideRecipeRepository(dataStore: RecipeDataStore) : RecipeRepository =
    RecipeRepositoryImpl(dataStore)
}
