package com.puroblast.tintest.features.top_films_feature.ui

import com.puroblast.tintest.features.top_films_feature.ui.recycler.FilmItem
import com.puroblast.tintest.features.top_films_feature.ui.recycler.NoConnectionItem
import com.puroblast.tintest.features.top_films_feature.ui.recycler.ProgressBarItem

data class TopFilmsUiState(
    val filmItems : List<FilmItem> = emptyList(),
    val errorItem : NoConnectionItem? = null,
    val progressBarItem : ProgressBarItem? = null
)
