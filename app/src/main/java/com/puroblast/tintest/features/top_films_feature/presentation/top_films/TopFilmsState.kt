package com.puroblast.tintest.features.top_films_feature.presentation.top_films

import com.puroblast.tintest.features.top_films_feature.ui.recycler.films.FilmItem
import com.puroblast.tintest.features.top_films_feature.ui.views.top_films.TopFilmsUiState
import com.puroblast.tintest.features.top_films_feature.ui.recycler.no_connection.NoConnectionItem
import com.puroblast.tintest.features.top_films_feature.ui.recycler.progress_bar.ProgressBarItem
import com.puroblast.tintest.utils.TopFilmsFeedState
import com.puroblast.tintest.utils.FilmFilter

data class TopFilmsState(
    val feedState: TopFilmsFeedState = TopFilmsFeedState.Loading,
    val selectedFilter: FilmFilter = FilmFilter.POPULAR
) {
    fun mapToUiState() : TopFilmsUiState {
        return when(feedState) {
            is TopFilmsFeedState.Content -> TopFilmsUiState(feedState.films.map {
                FilmItem(
                    it,
                    it.filmId,
                    it.nameRu,
                    it.genres,
                    it.year.toString(),
                    it.posterUrl ,
                    it.isFavourite) }
            )

            is TopFilmsFeedState.Error -> TopFilmsUiState(errorItem = NoConnectionItem())
            is TopFilmsFeedState.Loading -> TopFilmsUiState(progressBarItem = ProgressBarItem())
        }
    }
}
