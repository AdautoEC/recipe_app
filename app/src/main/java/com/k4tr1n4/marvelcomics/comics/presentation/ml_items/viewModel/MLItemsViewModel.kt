package com.k4tr1n4.marvelcomics.comics.presentation.ml_items.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.k4tr1n4.marvelcomics.comics.domain.use_case.GetItemsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds

@HiltViewModel
@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
class MLItemsViewModel @Inject constructor(
    getItemsUseCase: GetItemsUseCase,
): ViewModel() {
    private val _search = MutableStateFlow("")
    val searchState = _search.asStateFlow()

    val pagingState = _search
        .debounce(600.milliseconds)
        .flatMapLatest {
            getItemsUseCase(it).cachedIn(viewModelScope)
        }.cachedIn(viewModelScope)

    fun onSearchChange(value: String){
        _search.value = value
    }
}