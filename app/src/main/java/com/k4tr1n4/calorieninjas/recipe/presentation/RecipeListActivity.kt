package com.k4tr1n4.calorieninjas.recipe.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.k4tr1n4.calorieninjas.recipe.presentation.ui.screen.RecipeListScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RecipeListScreen()
        }
    }
}