package com.k4tr1n4.marvelcomics.comics.presentation.ui.screen.comics

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.k4tr1n4.marvelcomics.comics.presentation.viewModel.ComicsViewModel
import com.k4tr1n4.marvelcomics.core.network.model.getErrorThrowableOrNull
import com.k4tr1n4.marvelcomics.core.network.model.getSuccessDataOrNull
import com.k4tr1n4.marvelcomics.core.network.model.isLoading
import com.k4tr1n4.marvelcomics.core.ui.composable.DefaultScreen
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@RootNavGraph(start = true)
@Destination
@Composable
fun ComicsScreen(
    navigator: DestinationsNavigator,
    viewModel: ComicsViewModel = hiltViewModel()
){
    val state = viewModel.state.collectAsState()

    with(state.value){
        DefaultScreen(isLoading = isLoading(), errorThrowable = getErrorThrowableOrNull()){
            ComicsContent(
                recipe = getSuccessDataOrNull(),
                onClick = {}
            )
        }
    }
}