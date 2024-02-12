package com.puroblast.tintest.features.top_films_feature.presentation.top_films

import com.puroblast.tintest.features.top_films_feature.ui.views.top_films.TopFilmsUiState
import com.puroblast.tintest.utils.TopFilmsFeedState
import com.puroblast.tintest.utils.FilmFilter
import com.puroblast.tintest.utils.StateToUiStateMapper

data class TopFilmsState(
    val feedState: TopFilmsFeedState = TopFilmsFeedState.Loading,
    val selectedFilter: FilmFilter = FilmFilter.POPULAR
) {
    fun mapToUiState() : TopFilmsUiState {
        val uiStateMapper = StateToUiStateMapper()
        return uiStateMapper.mapTopFilmStateToUiState(feedState)
    }
}
