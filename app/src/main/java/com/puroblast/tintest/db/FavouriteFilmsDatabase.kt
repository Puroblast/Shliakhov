package com.puroblast.tintest.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.puroblast.tintest.domain.dao.FavouriteFilmsDao
import com.puroblast.tintest.domain.model.Film
import com.puroblast.tintest.utils.Converters

@Database(version = 1 , entities = [Film::class])
@TypeConverters(Converters::class)
abstract class FavouriteFilmsDatabase: RoomDatabase() {

    abstract val favouriteFilmsDao : FavouriteFilmsDao
}
