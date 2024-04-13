package com.k4tr1n4.marvelcomics.comics.presentation.ui.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.k4tr1n4.marvelcomics.comics.domain.model.ComicsModel

@Composable
fun ColumnScope.ComicsList(
    list: List<ComicsModel>,
    onClick: (ComicsModel) -> Unit,
){
    LazyColumn(
        modifier = Modifier.weight(1f),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ){
        items(items = list){
            ComicItem(
                comic = it,
                onClick = onClick
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ComicsListPreview(){
    Column(modifier = Modifier.fillMaxSize()) {
        ComicsList(
            list = listOf(
                ComicsModel(title = "Titulo", description = "descrição", ""),
                ComicsModel(title = "Titulo", description = "descrição", "")
            ),
            onClick = {}
        )
    }
}