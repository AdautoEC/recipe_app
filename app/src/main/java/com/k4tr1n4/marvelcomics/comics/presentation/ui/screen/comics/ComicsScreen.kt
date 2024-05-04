package com.k4tr1n4.marvelcomics.comics.presentation.ui.screen.comics

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
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
    val pagingState = viewModel.pagingState.collectAsLazyPagingItems()
    val activity = (LocalContext.current as? Activity)

    with(pagingState){
        DefaultScreen(
            isLoading = pagingState.loadState.refresh is LoadState.Loading ||
                    pagingState.loadState.append is LoadState.Loading,
            errorThrowable =
            if(pagingState.loadState.append is LoadState.Error)
                (pagingState.loadState.append as LoadState.Error).error
            else if(pagingState.loadState.refresh is LoadState.Error)
                (pagingState.loadState.refresh as LoadState.Error).error
            else null,
            onDismissError = {activity?.finishAffinity()}
        ){
            ComicsContent(
                pagingItems = this@with,
                onRefresh = pagingState::refresh,
                onClick = {}
            )
        }
    }
}