package com.puroblast.tintest.features.top_films_feature.presentation.about_film

import com.puroblast.tintest.features.top_films_feature.ui.views.about_film.AboutFilmUiState
import com.puroblast.tintest.utils.AboutFilmState
import com.puroblast.tintest.utils.StateToUiStateMapper

data class AboutFilmState(
    val aboutFilmState: AboutFilmState = AboutFilmState.Loading
) {
    fun mapToUiState() : AboutFilmUiState {
        val uiStateMapper = StateToUiStateMapper()
        return uiStateMapper.mapAboutFilmStateToUiState(aboutFilmState)
    }
}

