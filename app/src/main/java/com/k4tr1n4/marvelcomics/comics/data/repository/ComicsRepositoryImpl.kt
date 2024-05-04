package com.k4tr1n4.marvelcomics.comics.data.repository

import com.k4tr1n4.marvelcomics.comics.data.local.ComicsDao
import com.k4tr1n4.marvelcomics.comics.data.local.entity.mapper.asDomain
import com.k4tr1n4.marvelcomics.comics.data.local.entity.mapper.asEntity
import com.k4tr1n4.marvelcomics.comics.data.remote.ComicDataStore
import com.k4tr1n4.marvelcomics.comics.data.remote.model.ItemModel
import com.k4tr1n4.marvelcomics.comics.domain.repository.ComicsRepository
import com.k4tr1n4.marvelcomics.core.network.model.LoadingEvent
import com.k4tr1n4.marvelcomics.core.network.model.getSuccessDataOrNull
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ComicsRepositoryImpl @Inject constructor(
    private val dataStore: ComicDataStore,
    private val dao: ComicsDao
): ComicsRepository {
    override fun getComics(offset: Int?): Flow<LoadingEvent<ItemModel>> {
        return dataStore.getComics(offset)

        /*dao.getComicsList().map{daoEvent->
            when(daoEvent){
                is LoadingEvent.Loading -> {
                    emit(LoadingEvent.Loading)
                }
                is LoadingEvent.Success -> {
                    emit(LoadingEvent.Success(daoEvent.data.asDomain()))
                }
                is LoadingEvent.Error -> {
                    dataStore.getComics().map { dataStoreEvent->
                        when(dataStoreEvent){
                            is LoadingEvent.Success -> {
                                dao.insertComicsList(dataStoreEvent.data.asEntity())
                                emit(LoadingEvent.Success(dataStoreEvent.data))
                            }
                            else -> {emit(dataStoreEvent)}
                        }
                    }
                }
            }
        }*/

    }

}