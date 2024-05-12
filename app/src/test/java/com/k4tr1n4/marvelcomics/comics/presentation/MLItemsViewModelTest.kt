package com.k4tr1n4.marvelcomics.comics.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.k4tr1n4.marvelcomics.comics.domain.repository.MLRepository
import com.k4tr1n4.marvelcomics.comics.domain.use_case.GetItemsUseCase
import com.k4tr1n4.marvelcomics.comics.presentation.ml_items.viewModel.MLItemsViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class MLItemsViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: MLRepository

    private lateinit var useCase: GetItemsUseCase

    private lateinit var viewModel: MLItemsViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        useCase = GetItemsUseCase(repository)
        viewModel = MLItemsViewModel(useCase)
    }
}