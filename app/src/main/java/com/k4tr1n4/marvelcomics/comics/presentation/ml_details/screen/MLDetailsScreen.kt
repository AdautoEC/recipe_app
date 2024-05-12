package com.k4tr1n4.marvelcomics.comics.presentation.ml_details.screen

import androidx.compose.runtime.Composable
import com.k4tr1n4.marvelcomics.comics.domain.model.MLItemModel.Details
import com.k4tr1n4.marvelcomics.core.ui.composable.DefaultScreen
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun MLDetailsScreen(
    navigator: DestinationsNavigator,
    details: Details
){
    DefaultScreen{ MLDetailsContent(
        onBackClick = {navigator.navigateUp()},
        details = details
    ) }
}