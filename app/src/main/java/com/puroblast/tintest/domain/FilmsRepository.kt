package com.puroblast.tintest.domain

import com.puroblast.tintest.domain.dao.FavouriteFilmsDao
import com.puroblast.tintest.domain.model.Film
import com.puroblast.tintest.utils.FilmFilter
import com.puroblast.tintest.utils.MemoryStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class FilmsRepository(
    private val filmsApi: FilmsApi,
    private val filmsDao: FavouriteFilmsDao,
    private val memoryStorage: MemoryStorage
) {

    suspend fun getFilms(query: String, filmFilter: FilmFilter): List<Film> =
        withContext(Dispatchers.IO) {
            val films = when (filmFilter) {
                FilmFilter.POPULAR -> {
                    getPopularFilms()
                }

                FilmFilter.FAVOURITE -> filmsDao.getFavouriteFilms().sortedBy { it.position }.map { it.copy(isFavourite = true) }
            }
            films.filter { film -> film.nameRu.contains(query, ignoreCase = true) }
        }


    suspend fun getFilm(id: String): Film {
        val films =
            memoryStorage.getFilms()?.filter { it.filmId == id.toInt() && it.description != null }
        if (films.isNullOrEmpty()) {
            return runCatching {
                filmsApi.getFilm(id).also { memoryStorage.updateFilm(it) }
            }.getOrThrow()
        } else {
            return films.first()
        }
    }

    suspend fun setFavouriteFilm(film: Film) {
        filmsDao.setFavouriteFilm(film)
    }

    suspend fun deleteFavouriteFilm(film: Film) {
        filmsDao.deleteFavouriteFilm(film)
        memoryStorage.updateFilm(film.copy(isFavourite = false))
    }

    fun observeDatabaseChanges(): Flow<List<Film>> {
        return filmsDao.observeFavouriteFilms()
    }

    private suspend fun getFromRemote(): List<Film> = runCatching {
        filmsApi.getTopFilms().films.also {
            it.forEachIndexed { index, film -> film.position = index }
            memoryStorage.setFilms(it)
        }
    }.getOrThrow()

    private suspend fun getPopularFilms(): List<Film> {
        val favouriteFilms = filmsDao.getFavouriteFilms()
        val films = memoryStorage.getFilms() ?: getFromRemote()
        return films.map { film ->
            if (film in favouriteFilms) film.copy(isFavourite = true) else film
        }.also {
            memoryStorage.setFilms(it)
        }
    }
}
