package com.k4tr1n4.marvelcomics.comics.presentation.ui.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.k4tr1n4.marvelcomics.comics.domain.model.ComicsModel
import com.k4tr1n4.marvelcomics.core.ui.composable.AsyncImageShimmer

@Composable
fun ComicItem(
    comic: ComicsModel,
    onClick: (ComicsModel) -> Unit,
){
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.outlinedCardColors(containerColor = Color.White),
        onClick = {onClick(comic)}
    ) {
        Spacer(modifier = Modifier.height(8.dp))
        Row (
            modifier = Modifier.fillMaxSize()
        ){
            AsyncImageShimmer(
                modifier = Modifier.padding(horizontal = 8.dp),
                url = comic.pathImg
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = comic.title,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = comic.description,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun ComicItemPreview(){
   Box (modifier = Modifier.padding(16.dp)){
       ComicItem(
           comic = ComicsModel(title = "Titulo", description = "descrição", ""),
           onClick = {}
       )
   }
}