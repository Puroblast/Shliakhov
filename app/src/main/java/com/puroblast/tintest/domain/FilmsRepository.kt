package com.puroblast.tintest.domain

import com.puroblast.tintest.domain.dao.FavouriteFilmsDao
import com.puroblast.tintest.domain.model.Film
import com.puroblast.tintest.utils.FilmFilter
import com.puroblast.tintest.utils.MemoryStorage
import com.puroblast.tintest.utils.NoConnectionException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest

class FilmsRepository(
    private val filmsApi: FilmsApi,
    private val filmsDao: FavouriteFilmsDao,
    private val memoryStorage: MemoryStorage
) {

    fun observeFilms(query: String, filmFilter: FilmFilter): Flow<List<Film>> {
        return runCatching {
            filmsDao.getFavouriteFilms()
        }.getOrThrow().mapLatest { favouriteFilms ->
            getTopFilms().map { film ->
                if (film in favouriteFilms) film.copy(isFavourite = true) else film.copy(
                    isFavourite = false
                )
            }.filter {
                when (filmFilter) {
                    FilmFilter.FAVOURITE -> it.isFavourite && it.nameRu.contains(query, true)
                    FilmFilter.POPULAR -> {
                        it.nameRu.contains(query, true)
                    }
                }
            }
        }

    }

    suspend fun getFilm(id: String): Film {
        val films =
            memoryStorage.getFilms()?.filter { it.filmId == id.toInt() && it.description != null }
        if (films.isNullOrEmpty()) {
            return filmsApi.getFilm(id).also { memoryStorage.updateFilm(it) }
        } else {
            return films.first()
        }
    }

    private suspend fun getTopFilms(): List<Film> {
        return memoryStorage.getFilms()
            ?: filmsApi.getTopFilms().films.also { memoryStorage.setFilms(it) }
    }
}
