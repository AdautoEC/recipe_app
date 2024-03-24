package com.k4tr1n4.calorieninjas.recipe.di

import com.k4tr1n4.calorieninjas.recipe.data.remote.RecipeService
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
  fun provideRecipeService(retrofit: Retrofit): RecipeService {
    return retrofit.create(RecipeService::class.java)
  }
}
