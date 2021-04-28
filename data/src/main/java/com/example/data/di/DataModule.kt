package com.example.data.di

import com.apollographql.apollo.ApolloClient
import com.example.data.api.RemoteDataSource
import com.example.data.api.RemoteDataSourceImpl
import com.example.data.repositories.SearchRepositoryImpl
import com.example.domain.repositories.SearchRepository
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class DataModule {

    companion object {

        const val BASE_URL = "https://bahnql.herokuapp.com/graphql"
    }

    private val clientBuilder = OkHttpClient.Builder()

    @Provides
    @Singleton
    fun providesApolloClient(): ApolloClient =
        ApolloClient.builder()
            .serverUrl(BASE_URL)
            .okHttpClient(
                clientBuilder.callTimeout(1, TimeUnit.MINUTES)
                    .readTimeout(1, TimeUnit.MINUTES).build()
            )
            .build()

    @Provides
    @Singleton
    fun providesRemoteDataSource(apolloClient: ApolloClient): RemoteDataSource =
        RemoteDataSourceImpl(apolloClient)

    @Provides
    @Singleton
    fun providesSearchRepository(remoteDataSource: RemoteDataSource): SearchRepository =
        SearchRepositoryImpl(remoteDataSource)
}