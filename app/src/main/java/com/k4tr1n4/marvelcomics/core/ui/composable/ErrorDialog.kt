package com.k4tr1n4.marvelcomics.core.ui.composable

import android.widget.AutoCompleteTextView.OnDismissListener
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.k4tr1n4.marvelcomics.R

@Composable
fun ErrorDialog(
    title: String,
    description: String,
    buttonText: String,
    modifier: Modifier = Modifier,
    cancelable: Boolean = true,
    onDismiss: () -> Unit,
    onButtonClick: () -> Unit = {}
){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.bluer))
    ){
        Dialog(
            onDismissRequest = onDismiss,
            properties = DialogProperties(
                dismissOnBackPress = cancelable,
                dismissOnClickOutside = cancelable
            )
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                colors = CardDefaults.outlinedCardColors(containerColor = Color.White)
            ) {
                Column (
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleMedium
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = description,
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    TextButton(
                        onClick = {
                            onButtonClick()
                            onDismiss()
                        },
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Text(
                            text = buttonText,
                            style = MaterialTheme.typography.titleSmall
                        )
                    }
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun ErrorDialogPreview(){
    ErrorDialog(
        title = "Atenção",
        description = "Houve um erro ao acessar a api.\nPor favor tente novamente mais tarde",
        buttonText = "Fechar app",
        onDismiss = {},
        onButtonClick = {},
    )
}