package com.k4tr1n4.marvelcomics.comics.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ComicsEntity(
    @PrimaryKey val title: String,
    val description: String,
    val pathImg: String,
)
