package com.k4tr1n4.marvelcomics.comics.presentation.ui.screen.comics

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.k4tr1n4.marvelcomics.R
import com.k4tr1n4.marvelcomics.comics.domain.model.ComicsModel
import com.k4tr1n4.marvelcomics.comics.presentation.ui.composable.ComicsList
import com.k4tr1n4.marvelcomics.core.ui.composable.DefaultScreen

@Composable
fun ColumnScope.ComicsContent(
    recipe: List<ComicsModel>?,
    onClick: (ComicsModel) -> Unit,
){
    Text(
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.titleLarge,
        text = stringResource(id = R.string.comic_screen_title)
    )
    recipe?.let {
        ComicsList(
            list = recipe,
            onClick = onClick
        )
    } ?: kotlin.run { Spacer(modifier = Modifier.weight(1f)) }
}

@Composable
@Preview(showBackground = true)
fun ComicsContentPreview(){
    DefaultScreen(
        isLoading = false,
        errorThrowable = null,
        onDismissError = {}
    ) {
        ComicsContent(
            recipe = null,
            onClick = {}
        )
    }

}