package com.k4tr1n4.marvelcomics.comics.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.k4tr1n4.marvelcomics.comics.data.local.entity.ComicsEntity
import com.k4tr1n4.marvelcomics.comics.domain.model.ComicsModel
import com.k4tr1n4.marvelcomics.core.network.model.LoadingEvent
import kotlinx.coroutines.flow.Flow

@Dao
interface ComicsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertComicsList(comicsList: List<ComicsEntity>)

    @Query("SELECT * FROM ComicsEntity;")
    fun getComicsList(): Flow<LoadingEvent<List<ComicsEntity>>>
}