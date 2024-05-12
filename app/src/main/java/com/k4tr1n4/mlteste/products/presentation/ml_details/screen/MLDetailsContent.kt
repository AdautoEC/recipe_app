package com.k4tr1n4.mlteste.products.presentation.ml_details.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.k4tr1n4.mlteste.products.domain.model.MLItemModel.Details
import com.k4tr1n4.mlteste.products.domain.model.MLItemModel.Details.MoreDetails
import com.k4tr1n4.mlteste.products.presentation.ml_details.composable.MLDetailItem
import com.k4tr1n4.mlteste.core.extensions.formatMoney
import com.k4tr1n4.mlteste.products.presentation.ml_details.composable.MLDetailHeader

@Composable
fun ColumnScope.MLDetailsContent(
    details: Details,
    onBackClick: () -> Unit,
){
    val uriHandler = LocalUriHandler.current
    MLDetailHeader(
        title = details.title,
        onBackClick = onBackClick
    )

    LazyColumn (
        modifier = Modifier.weight(1f)
    ){
        item { MLDetailItem(label = "Valor", value = details.price.formatMoney()) }
        item { MLDetailItem(label = "Qtd.", value = details.availableQuantity.toString()) }
        item { MLDetailItem(label = "Condição", value = details.condition) }
        itemsIndexed(details.moreDetails){ index, item ->
            MLDetailItem(
                label = item.name,
                value = item.value,
                hasDivider = index < details.moreDetails.size - 1
            )
        }
    }

    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = { uriHandler.openUri(details.permalink) }
    ) {
        Text(text = "Veja no site")
    }
}

@Composable
@Preview(showBackground = true)
fun MLDetailsContentPreview(){
    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        MLDetailsContent(
           onBackClick = {},
           details = Details(
               title = "Titulo",
               price = 0.0,
               pathImg = "",
               permalink = "",
               condition = "",
               availableQuantity = 0,
               moreDetails = listOf(
                   MoreDetails(
                       name = "nome 1",
                       value = "valor 1"
                   ),
                   MoreDetails(
                       name = "nome 2",
                       value = "valor 2"
                   ),
                   MoreDetails(
                       name = "nome 3",
                       value = "valor 3"
                   )
               )
           )
        )
    }
}