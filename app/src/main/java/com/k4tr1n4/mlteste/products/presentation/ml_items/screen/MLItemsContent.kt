package com.k4tr1n4.mlteste.products.presentation.ml_items.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.k4tr1n4.mlteste.R
import com.k4tr1n4.mlteste.products.domain.model.MLItemModel
import com.k4tr1n4.mlteste.products.presentation.ml_items.composable.MLItem
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch

@Composable
fun ColumnScope.MLItemsContent(
    search: String,
    pagingItems: LazyPagingItems<MLItemModel>,
    onRefresh: () -> Unit,
    onSearchChange: (String) -> Unit,
    onClick: (MLItemModel) -> Unit,
){
    val swipeRefreshState = rememberSwipeRefreshState(false)
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    val keyboardController = LocalSoftwareKeyboardController.current

    Text(
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.titleLarge,
        text = stringResource(id = R.string.search_screen_title)
    )

    Spacer(Modifier.height(8.dp))

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = search,
        placeholder = {
            Text(text = stringResource(id = R.string.search_screen_field_placeholder))
        },
        onValueChange = onSearchChange,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = { keyboardController?.hide() }
        )
    )

    Spacer(Modifier.height(8.dp))

    SwipeRefresh(
        modifier = Modifier.weight(1f),
        state = swipeRefreshState,
        onRefresh = onRefresh,
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            state = listState,
        ){
            items(pagingItems.itemCount){index ->
                pagingItems[index]?.let {comic ->
                    MLItem(
                        mlItem = comic,
                        onClick = onClick
                    )
                }
            }
        }
    }

    Spacer(Modifier.height(8.dp))

    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = {
            coroutineScope.launch { listState.scrollToItem(index = 0) }
        }
    ) { Text(text = "Voltar ao topo") }

}

@Composable
@Preview(showBackground = true)
fun MLItemsContentPreview(){
    val pagingItems = flowOf(PagingData.empty<MLItemModel>()).collectAsLazyPagingItems()
    val search =  remember {
        mutableStateOf("")
    }
    Column (modifier = Modifier.fillMaxSize()){
        MLItemsContent(
            search = search.value,
            pagingItems = pagingItems,
            onRefresh = {},
            onClick = {},
            onSearchChange = {search.value = it}
        )
    }
}