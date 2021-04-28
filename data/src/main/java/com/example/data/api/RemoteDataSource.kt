package com.example.data.api

import com.example.data.api.model.ApiStation
import com.example.domain.model.Station
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {

    suspend fun search(city: String): Flow<List<Station>>
}