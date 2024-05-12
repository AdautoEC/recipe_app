package com.k4tr1n4.mlteste.products.presentation.ml_details.screen

import androidx.compose.runtime.Composable
import com.k4tr1n4.mlteste.products.domain.model.MLItemModel.Details
import com.k4tr1n4.mlteste.core.ui.composable.DefaultScreen
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