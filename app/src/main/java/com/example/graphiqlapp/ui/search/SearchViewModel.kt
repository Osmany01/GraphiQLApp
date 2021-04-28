package com.example.graphiqlapp.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Station
import com.example.domain.usecase.stations.GetStationsUseCase
import com.example.graphiqlapp.ui.common.GENERIC_ERROR_MESSAGE
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel @Inject constructor(private val getStationsUseCase: GetStationsUseCase) :
    ViewModel() {

    private val _spinner = MutableStateFlow(true)
    val spinner: StateFlow<Boolean> get() = _spinner

    private val _searchUiState =
        MutableStateFlow<SearchUiState>(SearchUiState.Empty)
    val searchUiState get() = _searchUiState


    init {
        viewModelScope.launch {

            search("")
        }
    }

    suspend fun search(city: String) {

        _spinner.value  = true

        getStationsUseCase.search(city).onEach {
            if (it.isNotEmpty()) _searchUiState.value = SearchUiState.Success(it)
            else _searchUiState.value = SearchUiState.Empty

        }.catch {
            _searchUiState.value = SearchUiState.Error(it.message ?: GENERIC_ERROR_MESSAGE)
        }.collect()

        _spinner.value = false
    }

    sealed class SearchUiState {
        data class Error(val message: String) : SearchUiState()
        object Empty : SearchUiState()
        data class Success(val listStations: List<Station>) : SearchUiState()
    }
}
