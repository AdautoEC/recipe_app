package com.k4tr1n4.marvelcomics

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.k4tr1n4.marvelcomics.comics.presentation.ui.screen.comics.NavGraphs
import com.k4tr1n4.marvelcomics.core.ui.theme.ComicMarvelTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FlowActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComicMarvelTheme {
                DestinationsNavHost(navGraph = NavGraphs.root)
            }
        }
    }
}