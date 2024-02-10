package com.puroblast.tintest.features.top_films_feature.presentation.about_film

import com.puroblast.tintest.features.top_films_feature.ui.AboutFilmUiState
import com.puroblast.tintest.utils.AboutFilmFeedState

data class AboutFilmState(
    val feedState: AboutFilmFeedState = AboutFilmFeedState.Loading
) {
    fun mapToUiState() : AboutFilmUiState {
        when(feedState) {
            is AboutFilmFeedState.Content -> return AboutFilmUiState(
                feedState.film.nameRu,
                feedState.film.description,
                feedState.film.genres.joinToString(", "),
                feedState.film.countries.joinToString(", "),
                feedState.film.posterUrl
            )
            else -> return AboutFilmUiState()
        }
    }
}

