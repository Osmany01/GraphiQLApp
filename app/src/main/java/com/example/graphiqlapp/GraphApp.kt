package com.example.graphiqlapp

import android.app.Application
import com.example.graphiqlapp.di.DaggerGraphAppComponent
import com.example.graphiqlapp.di.GraphAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class GraphApp: Application(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    lateinit var graphAppComponent: GraphAppComponent

    override fun androidInjector(): AndroidInjector<Any> {

        return androidInjector
    }

    override fun onCreate() {
        super.onCreate()

        graphAppComponent = DaggerGraphAppComponent.builder()
            .application(this)
            .build()

        graphAppComponent.inject(this)
    }
}