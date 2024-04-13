package com.k4tr1n4.marvelcomics.comics.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ComicsModel(
    val title: String,
    val description: String,
    val pathImg: String
): Parcelable

