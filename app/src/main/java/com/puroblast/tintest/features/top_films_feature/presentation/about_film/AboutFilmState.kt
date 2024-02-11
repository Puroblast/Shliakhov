package com.puroblast.tintest.features.top_films_feature.presentation.about_film

import com.puroblast.tintest.features.top_films_feature.ui.views.about_film.AboutFilmUiState
import com.puroblast.tintest.utils.AboutFilmState

data class AboutFilmState(
    val aboutFilmState: AboutFilmState = AboutFilmState.Loading
) {
    fun mapToUiState(): AboutFilmUiState {
        return when (aboutFilmState) {
            is AboutFilmState.Content -> AboutFilmUiState(
                aboutFilmState.film.nameRu,
                aboutFilmState.film.description,
                aboutFilmState.film.genres.map { it.genre }.joinToString(", "),
                aboutFilmState.film.countries.map { it.country }.joinToString(", "),
                aboutFilmState.film.posterUrl
            )

            is AboutFilmState.Error -> AboutFilmUiState(isError = true , isLoading = false)
            is AboutFilmState.Loading -> AboutFilmUiState(isLoading = true , isError = false)
        }
    }
}

