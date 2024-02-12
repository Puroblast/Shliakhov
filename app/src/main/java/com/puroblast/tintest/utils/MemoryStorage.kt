package com.puroblast.tintest.utils

import android.util.Log
import com.puroblast.tintest.domain.model.Film
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class MemoryStorage {
    private var value: List<Film>? = null
    private val mutex = Mutex()
    suspend fun getFilms(): List<Film>? = mutex.withLock { value }
    suspend fun setFilms(topFilms: List<Film>) = mutex.withLock { value = topFilms }

    suspend fun updateFilm(film : Film) = mutex.withLock {
        value?.map { if (it.filmId == film.filmId) {
            it.isFavourite = film.isFavourite
            it.description = film.description
        } }
    }
}
