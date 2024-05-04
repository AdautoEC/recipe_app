package com.k4tr1n4.marvelcomics.comics.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.k4tr1n4.marvelcomics.comics.data.local.entity.ComicsEntity

@Database(
    entities = [ComicsEntity::class],
    version = 0,
    exportSchema = true
)

abstract class MarvelDatabase : RoomDatabase() {

    abstract fun comicsDao(): ComicsDao
}