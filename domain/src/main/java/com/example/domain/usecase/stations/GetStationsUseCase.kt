package com.example.domain.usecase.stations

import com.example.domain.model.Station
import com.example.domain.repositories.SearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetStationsUseCase @Inject constructor(private val repo: SearchRepository) {

    suspend fun search(city: String): Flow<List<Station>> =
        repo.search(city)
}