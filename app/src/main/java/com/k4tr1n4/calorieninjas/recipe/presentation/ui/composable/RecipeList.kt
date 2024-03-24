package com.k4tr1n4.calorieninjas.recipe.presentation.ui.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.k4tr1n4.calorieninjas.core.ui.composable.AsyncImageShimmer
import com.k4tr1n4.calorieninjas.recipe.domain.model.RecipeModel

@Composable
fun RecipeItem(recipe: RecipeModel){
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.outlinedCardColors(containerColor = Color.White),
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            modifier = Modifier.padding(horizontal = 8.dp),
            text = recipe.label,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        Spacer(modifier = Modifier.height(8.dp))
        AsyncImageShimmer(
            modifier = Modifier.padding(horizontal = 8.dp),
            url = recipe.img
        )
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
fun ColumnScope.RecipeList(list: List<RecipeModel>){
    LazyVerticalGrid(
        modifier = Modifier.weight(1f),
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ){
        items(items = list){
            RecipeItem(recipe = it)
        }
    }
}