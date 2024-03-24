package com.k4tr1n4.calorieninjas.recipe.data.remote

import com.k4tr1n4.calorieninjas.core.network.model.LoadingEvent
import com.k4tr1n4.calorieninjas.recipe.data.model.RecipeResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RecipeDataStore @Inject constructor(
    private val service: RecipeService
) {
    fun getRecipe(recipe: String): Flow<LoadingEvent<RecipeResponse>> {
        return service.getRecipe(recipe = recipe)
    }
}