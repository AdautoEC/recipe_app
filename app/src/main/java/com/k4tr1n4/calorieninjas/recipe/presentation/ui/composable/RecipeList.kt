package com.k4tr1n4.calorieninjas.recipe.presentation.ui.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
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
        Row (
            modifier = Modifier.fillMaxSize()
        ){
            AsyncImageShimmer(
                modifier = Modifier.padding(horizontal = 8.dp),
                url = recipe.img
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = recipe.label,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = recipe.ingredients,
                    maxLines = 6,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
fun ColumnScope.RecipeList(list: List<RecipeModel>){
    LazyColumn(
        modifier = Modifier.weight(1f),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ){
        items(items = list){
            RecipeItem(recipe = it)
        }
    }
}