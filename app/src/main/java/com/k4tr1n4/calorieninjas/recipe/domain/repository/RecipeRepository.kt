package com.k4tr1n4.calorieninjas.recipe.domain.repository

import com.k4tr1n4.calorieninjas.core.network.model.LoadingEvent
import com.k4tr1n4.calorieninjas.recipe.data.model.RecipeResponse
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {
    fun getRecipe(recipe: String): Flow<LoadingEvent<RecipeResponse>>
}