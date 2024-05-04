package com.k4tr1n4.marvelcomics.comics.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.k4tr1n4.marvelcomics.comics.domain.use_case.GetComicsUseCase
import com.k4tr1n4.marvelcomics.core.network.model.LoadingEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ComicsViewModel @Inject constructor(
    getComicsUseCase: GetComicsUseCase,
): ViewModel() {
    /*val state = getComicsUseCase().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        LoadingEvent.Loading
    )*/

    val pagingState = getComicsUseCase().cachedIn(viewModelScope)
}