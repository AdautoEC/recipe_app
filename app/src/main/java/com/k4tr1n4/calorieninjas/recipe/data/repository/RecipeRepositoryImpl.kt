package com.k4tr1n4.calorieninjas.recipe.data.repository

import com.k4tr1n4.calorieninjas.core.network.model.LoadingEvent
import com.k4tr1n4.calorieninjas.recipe.data.model.RecipeResponse
import com.k4tr1n4.calorieninjas.recipe.data.remote.RecipeDataStore
import com.k4tr1n4.calorieninjas.recipe.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    private val dataStore: RecipeDataStore
): RecipeRepository {
    override fun getRecipe(recipe: String): Flow<LoadingEvent<RecipeResponse>> {
        //TODO(Add recipe cache)
        return dataStore.getRecipe(recipe)
    }
}