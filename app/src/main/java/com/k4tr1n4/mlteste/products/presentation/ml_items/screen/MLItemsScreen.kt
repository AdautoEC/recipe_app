package com.k4tr1n4.mlteste.products.presentation.ml_items.screen

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.k4tr1n4.mlteste.products.presentation.destinations.MLDetailsScreenDestination
import com.k4tr1n4.mlteste.products.presentation.ml_items.viewModel.MLItemsViewModel
import com.k4tr1n4.mlteste.core.extensions.getError
import com.k4tr1n4.mlteste.core.extensions.isLoading
import com.k4tr1n4.mlteste.core.ui.composable.DefaultScreen
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@RootNavGraph(start = true)
@Destination
@Composable
fun MLItemsScreen(
    navigator: DestinationsNavigator,
    viewModel: MLItemsViewModel = hiltViewModel()
){
    val pagingState = viewModel.pagingState.collectAsLazyPagingItems()
    val searchState = viewModel.searchState.collectAsState()
    val activity = (LocalContext.current as? Activity)

    with(pagingState){
        DefaultScreen(
            isLoading = pagingState.isLoading(),
            errorThrowable = pagingState.getError(),
            onDismissError = {activity?.finishAffinity()}
        ){
            MLItemsContent(
                search = searchState.value,
                pagingItems = this@with,
                onRefresh = pagingState::refresh,
                onClick = {navigator.navigate(MLDetailsScreenDestination(it.details))},
                onSearchChange = viewModel::onSearchChange
            )
        }
    }
}