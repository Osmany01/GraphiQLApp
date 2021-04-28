package com.example.data.api

import com.example.domain.model.Station
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeRemoteDataSourceImpl(
    private val stations: List<Station>,
    private val delay: Long = 0
): RemoteDataSource {
    override suspend fun search(city: String): Flow<List<Station>> {
        delay(delay)
        return  flowOf(stations)
    }


}