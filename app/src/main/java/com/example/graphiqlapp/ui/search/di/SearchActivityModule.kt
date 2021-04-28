package com.example.graphiqlapp.ui.search.di

import com.example.graphiqlapp.ui.search.SearchActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class SearchActivityModule {

    @ContributesAndroidInjector
    abstract fun provideSearchActivity(): SearchActivity
}