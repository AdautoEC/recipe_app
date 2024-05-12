package com.k4tr1n4.mlteste.core.extensions

import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems

fun <T : Any> LazyPagingItems<T>.isLoading(): Boolean =
    this.loadState.refresh is LoadState.Loading ||
        this.loadState.append is LoadState.Loading
fun <T : Any> LazyPagingItems<T>.getError(): Throwable? =
    if(this.loadState.append is LoadState.Error)
            (this.loadState.append as LoadState.Error).error
    else if(this.loadState.refresh is LoadState.Error)
            (this.loadState.refresh as LoadState.Error).error
    else null

