package com.plumcookingwine.jetpack.di.repository

import com.plumcookingwine.jetpack.data.JetPackFactory
import com.plumcookingwine.jetpack.data.database.AppDatabase
import com.plumcookingwine.jetpack.data.repository.Repository
import com.plumcookingwine.jetpack.network.AndroidWanService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideTasksRepository(service: AndroidWanService): Repository {
        return JetPackFactory.makePokemonRepository(service)
    }

}