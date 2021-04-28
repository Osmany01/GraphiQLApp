package com.example.graphiqlapp.ui.search.di

import com.example.domain.repositories.SearchRepository
import com.example.domain.usecase.stations.GetStationsUseCase
import dagger.Module
import dagger.Provides

@Module
class SearchModule {

    @Provides
    fun providesSearchUseCase(repo: SearchRepository): GetStationsUseCase =
        GetStationsUseCase(repo)
}