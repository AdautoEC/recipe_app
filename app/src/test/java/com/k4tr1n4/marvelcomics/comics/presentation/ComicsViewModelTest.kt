package com.k4tr1n4.marvelcomics.comics.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.viewModelScope
import app.cash.turbine.test
import com.k4tr1n4.marvelcomics.comics.domain.model.ComicsModel
import com.k4tr1n4.marvelcomics.comics.domain.repository.ComicsRepository
import com.k4tr1n4.marvelcomics.comics.domain.use_case.GetComicsUseCase
import com.k4tr1n4.marvelcomics.comics.presentation.viewModel.ComicsViewModel
import com.k4tr1n4.marvelcomics.comics.util.MockUtil
import com.k4tr1n4.marvelcomics.core.network.model.LoadingEvent
import com.k4tr1n4.marvelcomics.core.network.model.getSuccessDataOrNull
import com.nhaarman.mockitokotlin2.atLeastOnce
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.MainCoroutineDispatcher
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import kotlin.time.DurationUnit
import kotlin.time.toDuration

@ExperimentalCoroutinesApi
class ComicsViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: ComicsRepository

    private lateinit var useCase: GetComicsUseCase

    private lateinit var viewModel: ComicsViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        useCase = GetComicsUseCase(repository)
        viewModel = ComicsViewModel(useCase)
    }
}