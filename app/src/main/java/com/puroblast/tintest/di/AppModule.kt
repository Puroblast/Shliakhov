package com.puroblast.tintest.di

import com.puroblast.tintest.domain.FilmsApi
import com.puroblast.tintest.domain.FilmsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [DatabaseModule::class, NetworkModule::class])
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideFilmsRepository(filmsApi: FilmsApi) : FilmsRepository {
        return FilmsRepository(filmsApi)
    }
}
