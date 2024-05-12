package com.k4tr1n4.marvelcomics.comics.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MLItemModel(
    val title: String,
    val price: Double,
    val pathImg: String,
    val details: Details
): Parcelable{
    @Parcelize
    data class Details(
        val title: String,
        val price: Double,
        val pathImg: String,
        val permalink: String,
        val condition: String,
        val availableQuantity: Int,
        val moreDetails: List<MoreDetails>
    ): Parcelable{
        @Parcelize
        data class MoreDetails(
            val name: String,
            val value: String
        ): Parcelable
    }
}

