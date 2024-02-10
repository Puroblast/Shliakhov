package com.puroblast.tintest.features.top_films_feature.ui.views.top_films

import com.puroblast.tintest.features.top_films_feature.ui.recycler.films.FilmItem
import com.puroblast.tintest.features.top_films_feature.ui.recycler.no_connection.NoConnectionItem
import com.puroblast.tintest.features.top_films_feature.ui.recycler.progress_bar.ProgressBarItem

data class TopFilmsUiState(
    val filmItems : List<FilmItem> = emptyList(),
    val errorItem : NoConnectionItem? = null,
    val progressBarItem : ProgressBarItem? = null
)
