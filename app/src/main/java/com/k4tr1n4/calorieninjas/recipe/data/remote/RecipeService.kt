package com.k4tr1n4.calorieninjas.recipe.data.remote

import com.k4tr1n4.calorieninjas.core.network.model.LoadingEvent
import com.k4tr1n4.calorieninjas.recipe.data.model.RecipeResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeService {
    /*val app_key = "09df302deabb60af68da086993d52672"
    val app_id = "cb1fe6cb"*/
    @GET("search")
    fun getRecipe(
        @Query("app_id") id: String = "cb1fe6cb",
        @Query("app_key") key: String = "09df302deabb60af68da086993d52672",
        @Query("q") recipe: String,
    ): Flow<LoadingEvent<RecipeResponse>>
}