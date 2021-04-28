package com.example.graphiqlapp.search

import com.example.data.repositories.SearchRepositoryImpl
import com.example.domain.model.Station
import com.example.domain.usecase.stations.GetStationsUseCase
import com.example.graphiqlapp.common.CoroutinesUiTestRule
import com.example.graphiqlapp.common.FakeRemoteDataSourceImpl
import com.example.graphiqlapp.ui.search.SearchViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class SearchViewModelTest {

    @get:Rule
    val coroutinesTestRule = CoroutinesUiTestRule()

    private val fakeStationList = listOf(
        Station("Station1", 1.000, 2.000, "pic1"),
        Station("Station2", 1.000, 2.000, "pic2"),
        Station("Station3", 1.000, 2.000, "pic3"),
        Station("Station4", 1.000, 2.000, "pic4")
    )

    private val successPath = SearchViewModel.SearchUiState.Success(fakeStationList)
    private val emptyPath = SearchViewModel.SearchUiState.Empty

    private val fakeRemoteDataSourceImpl = FakeRemoteDataSourceImpl(fakeStationList)

    private val repositoryImpl = SearchRepositoryImpl(fakeRemoteDataSourceImpl)
    private val getStationsUseCase = GetStationsUseCase(repositoryImpl)

    @Test
    fun `listening to repos flow emits the list of repos from server`() = runBlockingTest {
        val viewModel = SearchViewModel(getStationsUseCase)

        Assert.assertEquals(viewModel.searchUiState.value, successPath)
    }
    @Test
    fun `first element name should be the Station1`() = runBlockingTest {



        val viewModel = SearchViewModel(getStationsUseCase)
        Assert.assertEquals((viewModel.searchUiState.value as SearchViewModel.SearchUiState.Success).listStations.first().name, "Station1")

    }

}