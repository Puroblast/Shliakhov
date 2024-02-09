package com.puroblast.tintest.domain

import com.puroblast.tintest.domain.model.Film
import com.puroblast.tintest.domain.model.Films
import kotlinx.coroutines.flow.Flow

class FilmsRepository(private val filmsApi: FilmsApi) {

    suspend fun getTopFilms() : Films {
        return filmsApi.getTopFilms()
    }

    suspend fun getFilm(id : String) : Film {
        return filmsApi.getFilm(id)
    }
}
