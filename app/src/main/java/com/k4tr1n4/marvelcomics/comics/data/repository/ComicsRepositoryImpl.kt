package com.k4tr1n4.marvelcomics.comics.data.repository

import com.k4tr1n4.marvelcomics.comics.data.remote.ComicDataStore
import com.k4tr1n4.marvelcomics.comics.data.remote.model.ItemModel
import com.k4tr1n4.marvelcomics.comics.domain.repository.ComicsRepository
import com.k4tr1n4.marvelcomics.core.network.model.LoadingEvent
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ComicsRepositoryImpl @Inject constructor(
    private val dataStore: ComicDataStore,
): ComicsRepository {
    override fun getComics(): Flow<LoadingEvent<ItemModel>>{
        /*return dao.getComicsList().map{
            when(it){
                is LoadingEvent.Loading -> {
                    flowOf(LoadingEvent.Loading)
                }
                is LoadingEvent.Success -> {
                    flowOf(LoadingEvent.Success(it.data.asDomain()))
                }
                is LoadingEvent.Error -> {
                    flowOf(dataStore.getComics())
                }
            }
        }*/



        return dataStore.getComics()
    }

}