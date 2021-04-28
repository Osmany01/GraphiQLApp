package com.example.data.repositories

import com.example.data.api.RemoteDataSource
import com.example.domain.model.Station
import com.example.domain.repositories.SearchRepository
import kotlinx.coroutines.flow.Flow
import javax.sql.DataSource

class SearchRepositoryImpl(private val remoteDataSource: RemoteDataSource): SearchRepository {

    override suspend fun search(city: String): Flow<List<Station>> =
        remoteDataSource.search(city)

}