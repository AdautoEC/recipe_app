package com.k4tr1n4.calorieninjas.recipe.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.k4tr1n4.calorieninjas.core.network.model.SubmitLoadingState
import com.k4tr1n4.calorieninjas.core.network.model.submittable
import com.k4tr1n4.calorieninjas.recipe.domain.use_case.GetRecipeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class RecipeListViewModel @Inject constructor(
    getRecipeUseCase: GetRecipeUseCase
): ViewModel() {
    var searchValue = MutableStateFlow("")

    private val recipeSubmittable = submittable { getRecipeUseCase(searchValue.value) }

    val state = recipeSubmittable.flow.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        SubmitLoadingState.Loading
    )

    fun requestRecipe(){
        recipeSubmittable.submit()
    }

    @Suppress("unused")
    fun resetRecipe(){
        recipeSubmittable.reset()
    }

    fun onSearchValueChange(newValue: String){
        searchValue.value = newValue
    }
}