package com.example.domain.repositories

import com.example.domain.model.Station
import kotlinx.coroutines.flow.Flow

interface SearchRepository {

    suspend fun search(city: String): Flow<List<Station>>
}