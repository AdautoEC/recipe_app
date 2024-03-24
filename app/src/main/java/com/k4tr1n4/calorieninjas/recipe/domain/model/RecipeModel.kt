package com.k4tr1n4.calorieninjas.recipe.domain.model

import com.k4tr1n4.calorieninjas.recipe.data.model.RecipeResponse

data class RecipeModel(
    val recipe: RecipeResponse.Hit.Recipe,
    val img: String,
    val label: String,
    val ingredients: String
)
