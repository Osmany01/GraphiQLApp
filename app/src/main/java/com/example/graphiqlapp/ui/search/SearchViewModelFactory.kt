package com.example.graphiqlapp.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.usecase.stations.GetStationsUseCase

class SearchViewModelFactory(private val getStationsUseCase: GetStationsUseCase): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
            return SearchViewModel(
                getStationsUseCase
            ) as T
        }
        throw IllegalAccessException("Unknown ViewModel class")
    }
}