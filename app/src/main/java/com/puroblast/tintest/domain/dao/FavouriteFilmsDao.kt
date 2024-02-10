package com.puroblast.tintest.domain.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.puroblast.tintest.domain.model.Film
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import retrofit2.http.GET

@Dao
interface FavouriteFilmsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setFavouriteFilm(film: Film)

    @Delete
    suspend fun deleteFavouriteFilm(film: Film)

    @GET
    fun getFavouriteFilms() : Flow<List<Film>> = flowOf(emptyList())
}
