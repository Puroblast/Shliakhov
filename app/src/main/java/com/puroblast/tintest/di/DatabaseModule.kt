package com.puroblast.tintest.di

import android.app.Application
import androidx.room.Room
import com.puroblast.tintest.db.FavouriteFilmsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideFavouriteFilmsDatabase(application: Application) : FavouriteFilmsDatabase {
        val database = Room.databaseBuilder(
            application,
            FavouriteFilmsDatabase::class.java,
            "favourite_films_db"
        ).build()
        return database
    }
}
