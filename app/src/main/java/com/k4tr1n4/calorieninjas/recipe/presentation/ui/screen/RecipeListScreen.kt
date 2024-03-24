package com.k4tr1n4.calorieninjas.recipe.presentation.ui.screen

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.k4tr1n4.calorieninjas.core.network.model.getErrorThrowableOrNull
import com.k4tr1n4.calorieninjas.core.network.model.getSuccessDataOrNull
import com.k4tr1n4.calorieninjas.core.network.model.isLoading
import com.k4tr1n4.calorieninjas.core.ui.composable.DefaultTextField
import com.k4tr1n4.calorieninjas.core.ui.composable.Loading
import com.k4tr1n4.calorieninjas.recipe.domain.model.RecipeModel
import com.k4tr1n4.calorieninjas.recipe.presentation.ui.composable.RecipeList
import com.k4tr1n4.calorieninjas.recipe.presentation.viewModel.RecipeListViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph

@RootNavGraph(start = true)
@Destination
@Composable
fun RecipeListScreen(
    viewModel: RecipeListViewModel = hiltViewModel()
){
    val state = viewModel.state.collectAsState()

    RecipeListContent(
        searchValue = viewModel.searchValue.value,
        recipe = state.value.getSuccessDataOrNull(),
        onSearchValueChange = viewModel::onSearchValueChange,
        onSearch = viewModel::requestRecipe
    )

    if(state.value.isLoading()){
        Loading()
    }

    state.value.getErrorThrowableOrNull()?.message?.let {
        Log.e("RecipeListScreen", it)
    }
}

@Composable
fun RecipeListContent(
    searchValue: String,
    recipe: List<RecipeModel>?,
    onSearchValueChange: (String) -> Unit,
    onSearch: () -> Unit
){
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            DefaultTextField(
                modifier = Modifier.fillMaxWidth(),
                value = searchValue,
                onValueChange = onSearchValueChange,
                onDone = onSearch
            )

            Spacer(modifier = Modifier.height(8.dp))

            recipe?.let { RecipeList(list = recipe) }
                ?: kotlin.run { Spacer(modifier = Modifier.weight(1f)) }

            Button(
                onClick = onSearch,
                modifier = Modifier
                    .fillMaxWidth()
            ) { Text(text = "Pesquisar") }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun RecipeListContentPreview(){
    RecipeListContent(
        searchValue = "",
        onSearchValueChange = {},
        recipe = null,
        onSearch = {}
    )
}

