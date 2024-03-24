package com.k4tr1n4.calorieninjas.recipe.domain.use_case

import com.k4tr1n4.calorieninjas.core.network.model.LoadingEvent
import com.k4tr1n4.calorieninjas.core.network.model.mapSuccess
import com.k4tr1n4.calorieninjas.recipe.domain.model.RecipeModel
import com.k4tr1n4.calorieninjas.recipe.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRecipeUseCase @Inject constructor(private val repository: RecipeRepository) {
    operator fun invoke(recipe: String): Flow<LoadingEvent<List<RecipeModel>>> {
        return repository.getRecipe(recipe).mapSuccess {
            it.hits.map { hit ->
                RecipeModel(
                    recipe = hit.recipe,
                    img = hit.recipe.image,
                    label = hit.recipe.label
                )
            }
        }
    }
}