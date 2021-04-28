package com.example.data.api

import SearchQuery
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.coroutines.await
import com.example.data.api.model.ApiStation
import com.example.data.toDomain
import com.example.domain.model.Station
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class RemoteDataSourceImpl(private val apolloClient: ApolloClient) : RemoteDataSource {

    override suspend fun search(city: String): Flow<List<Station>> {


        val response = apolloClient.query(SearchQuery.builder().searchTerm(if(city.isBlank()) "Flughafen" else city).build()).await()

        val responseStation = response.data?.search()?.stations()
        val parseListStation = mutableListOf<ApiStation>()
        responseStation?.forEach {
            parseListStation.add(ApiStation(it.name(), it.location()!!.latitude(), it.location()!!.longitude(), it?.picture()?.url()?:""))
        }

        return flowOf(parseListStation.map { it.toDomain() })
    }
}