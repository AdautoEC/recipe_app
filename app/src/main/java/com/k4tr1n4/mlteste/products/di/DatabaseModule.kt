package com.k4tr1n4.mlteste.products.di

import android.app.Application
import androidx.room.Room
import com.k4tr1n4.mlteste.products.data.local.ComicsDao
import com.k4tr1n4.mlteste.products.data.local.MarvelDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(
        application: Application,
    ): MarvelDatabase{
        return Room
            .databaseBuilder(application,MarvelDatabase::class.java,"ML.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideComicsDao(appDatabase: MarvelDatabase): ComicsDao {
        return appDatabase.comicsDao()
    }

}