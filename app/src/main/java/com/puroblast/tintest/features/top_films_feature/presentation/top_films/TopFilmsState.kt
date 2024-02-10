package com.puroblast.tintest.features.top_films_feature.presentation.top_films

import com.puroblast.tintest.features.top_films_feature.presentation.FilmItem
import com.puroblast.tintest.features.top_films_feature.ui.TopFilmsUiState
import com.puroblast.tintest.utils.TopFilmsFeedState
import com.puroblast.tintest.utils.FilmFilter

data class TopFilmsState(
    val feedState: TopFilmsFeedState = TopFilmsFeedState.Loading,
    val selectedFilter: FilmFilter = FilmFilter.POPULAR
) {
    fun mapToUiState() : TopFilmsUiState {
        when(feedState) {
            is TopFilmsFeedState.Content -> return TopFilmsUiState(feedState.films.map {
                FilmItem(
                    it.filmId,
                    it.nameRu,
                    it.genres,
                    it.year.toString(),
                    it.posterUrl ,
                    it.isFavourite) }
            ) else -> return TopFilmsUiState(emptyList())
        }
    }
}
