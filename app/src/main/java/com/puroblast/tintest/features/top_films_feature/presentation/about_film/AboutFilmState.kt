package com.puroblast.tintest.features.top_films_feature.presentation.about_film

import com.puroblast.tintest.features.top_films_feature.ui.views.about_film.AboutFilmUiState
import com.puroblast.tintest.utils.AboutFilmState

data class AboutFilmState(
    val feedState: AboutFilmState = AboutFilmState.Loading
) {
    fun mapToUiState(): AboutFilmUiState {
        return when (feedState) {
            is AboutFilmState.Content -> AboutFilmUiState(
                feedState.film.nameRu,
                feedState.film.description,
                feedState.film.genres.map { it.genre }.joinToString(", "),
                feedState.film.countries.map { it.country }.joinToString(", "),
                feedState.film.posterUrl
            )

            is AboutFilmState.Error -> AboutFilmUiState(isError = true)
            is AboutFilmState.Loading -> AboutFilmUiState(isLoading = true)
        }
    }
}

