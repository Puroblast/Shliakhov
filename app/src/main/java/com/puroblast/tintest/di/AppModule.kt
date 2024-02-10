package com.puroblast.tintest.di

import com.puroblast.tintest.db.FavouriteFilmsDatabase
import com.puroblast.tintest.domain.FilmsApi
import com.puroblast.tintest.domain.FilmsRepository
import com.puroblast.tintest.domain.dao.FavouriteFilmsDao
import com.puroblast.tintest.utils.MemoryStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module(includes = [DatabaseModule::class, NetworkModule::class])
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideFilmsRepository(filmsApi: FilmsApi , filmsDao: FavouriteFilmsDao, memoryStorage: MemoryStorage) : FilmsRepository {
        return FilmsRepository(filmsApi, filmsDao , memoryStorage)
    }

    @Provides
    @Singleton
    fun provideFilmsDao(db : FavouriteFilmsDatabase) : FavouriteFilmsDao {
        return db.favouriteFilmsDao
    }

    @Provides
    @Singleton
    fun provideMemoryStorage() : MemoryStorage {
        return MemoryStorage()
    }
}
