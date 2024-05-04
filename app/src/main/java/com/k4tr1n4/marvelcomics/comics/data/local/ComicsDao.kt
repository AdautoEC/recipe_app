package com.k4tr1n4.marvelcomics.comics.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.k4tr1n4.marvelcomics.comics.data.local.entity.ComicsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ComicsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertComicsList(comicsList: List<ComicsEntity>)

    @Query("SELECT * FROM ComicsEntity;")
    fun getComicsList(): Flow<List<ComicsEntity>>
}