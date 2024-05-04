package com.k4tr1n4.marvelcomics.comics.presentation.ui.screen.comics

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.k4tr1n4.marvelcomics.R
import com.k4tr1n4.marvelcomics.comics.domain.model.ComicsModel
import com.k4tr1n4.marvelcomics.comics.presentation.ui.composable.ComicItem
import com.k4tr1n4.marvelcomics.comics.presentation.ui.composable.ComicsList
import com.k4tr1n4.marvelcomics.core.ui.composable.DefaultScreen

@Composable
fun ColumnScope.ComicsContent(
    pagingItems: LazyPagingItems<ComicsModel>,
    onRefresh: () -> Unit,
    onClick: (ComicsModel) -> Unit,
){
    val swipeRefreshState = rememberSwipeRefreshState(false)
    Text(
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.titleLarge,
        text = stringResource(id = R.string.comic_screen_title)
    )

    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = onRefresh,
    ) {
        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ){
            items(pagingItems.itemCount){index ->
                pagingItems[index]?.let {comic ->
                    ComicItem(
                        comic = comic,
                        onClick = onClick
                    )
                }
            }
        }
    }

}

@Composable
@Preview(showBackground = true)
fun ComicsContentPreview(){
    DefaultScreen(
        isLoading = false,
        errorThrowable = null,
        onDismissError = {}
    ) {

    }

}