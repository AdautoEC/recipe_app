package com.k4tr1n4.mlteste.products.presentation.ml_items.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.k4tr1n4.mlteste.products.domain.model.MLItemModel
import com.k4tr1n4.mlteste.core.extensions.formatMoney
import com.k4tr1n4.mlteste.core.ui.composable.AsyncImageShimmer

@Composable
fun MLItem(
    mlItem: MLItemModel,
    onClick: (MLItemModel) -> Unit,
){
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.outlinedCardColors(containerColor = Color.White),
        onClick = {onClick(mlItem)}
    ) {
        Row (
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            AsyncImageShimmer(
                modifier = Modifier.padding(horizontal = 8.dp),
                url = mlItem.pathImg
            )
            Column(
                modifier = Modifier.padding(horizontal = 8.dp)
            ) {
                Text(
                    text = mlItem.title,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleMedium,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = mlItem.price.formatMoney(),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MLItemPreview(){
   Box (modifier = Modifier.padding(16.dp)){
       MLItem(
           mlItem = MLItemModel(
               title = "Titulo",
               price = 0.0,
               pathImg = "",
               details = MLItemModel.Details(
                   title = "Titulo",
                   price = 0.0,
                   pathImg = "",
                   permalink = "",
                   condition = "",
                   availableQuantity = 0,
                   moreDetails = listOf()
               )
           ),
           onClick = {}
       )
   }
}