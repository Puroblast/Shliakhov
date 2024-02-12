package com.puroblast.tintest.utils

import com.puroblast.tintest.features.top_films_feature.ui.recycler.films.FilmItem
import com.puroblast.tintest.features.top_films_feature.ui.recycler.no_connection.NoConnectionItem
import com.puroblast.tintest.features.top_films_feature.ui.recycler.progress_bar.ProgressBarItem
import com.puroblast.tintest.features.top_films_feature.ui.views.about_film.AboutFilmUiState
import com.puroblast.tintest.features.top_films_feature.ui.views.top_films.TopFilmsUiState

class StateToUiStateMapper {

    fun mapTopFilmStateToUiState(feedState: TopFilmsFeedState) : TopFilmsUiState {
        return when(feedState) {
            is TopFilmsFeedState.Content -> TopFilmsUiState(feedState.films.map {
                FilmItem(
                    it,
                    it.filmId,
                    it.nameRu,
                    it.genres,
                    it.year.toString(),
                    it.posterUrl ,
                    it.isFavourite
                ) }
            )

            is TopFilmsFeedState.Error -> TopFilmsUiState(errorItem = NoConnectionItem())
            is TopFilmsFeedState.Loading -> TopFilmsUiState(progressBarItem = ProgressBarItem())
        }
    }

    fun mapAboutFilmStateToUiState(aboutFilmState: AboutFilmState) : AboutFilmUiState {
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
