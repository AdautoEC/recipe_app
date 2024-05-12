package com.k4tr1n4.mlteste.products.presentation.ml_items.composable

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
import com.k4tr1n4.mlteste.products.domain.model.MLItemModel
import com.k4tr1n4.mlteste.products.domain.model.MLItemModel.Details

@Composable
fun ColumnScope.MLList(
    list: List<MLItemModel>,
    onClick: (MLItemModel) -> Unit,
){
    LazyColumn(
        modifier = Modifier.weight(1f),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ){
        items(items = list){
            MLItem(
                mlItem = it,
                onClick = onClick
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MLListPreview(){
    Column(modifier = Modifier.fillMaxSize()) {
        MLList(
            list = listOf(
                MLItemModel(
                    title = "Titulo",
                    price = 0.0,
                    pathImg = "",
                    details = Details(
                        title = "Titulo",
                        price = 0.0,
                        pathImg = "",
                        permalink = "",
                        condition = "",
                        availableQuantity = 0,
                        moreDetails = listOf()
                    )
                ),
                MLItemModel(
                    title = "Titulo",
                    price = 0.0,
                    pathImg = "",
                    details = Details(
                        title = "Titulo",
                        price = 0.0,
                        pathImg = "",
                        permalink = "",
                        condition = "",
                        availableQuantity = 0,
                        moreDetails = listOf()
                    )
                )
            ),
            onClick = {}
        )
    }
}