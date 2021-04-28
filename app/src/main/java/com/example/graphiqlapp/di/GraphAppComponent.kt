package com.example.graphiqlapp.di

import android.app.Application
import com.example.data.di.DataModule
import com.example.graphiqlapp.GraphApp
import com.example.graphiqlapp.ui.search.di.SearchActivityModule
import com.example.graphiqlapp.ui.search.di.SearchModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidInjectionModule::class,
        ViewModelModule::class,
        SearchActivityModule::class,
        SearchModule::class,
        DataModule::class]
)
interface GraphAppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: Application): Builder

        fun build(): GraphAppComponent
    }

    fun inject(app: GraphApp)
}