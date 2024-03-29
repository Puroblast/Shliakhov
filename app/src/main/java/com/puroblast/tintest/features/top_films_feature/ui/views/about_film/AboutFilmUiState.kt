package com.puroblast.tintest.features.top_films_feature.ui.views.about_film

data class AboutFilmUiState(
    val filmName : String = "",
    val filmDescription : String? = "",
    val filmGenres : String = "",
    val filmCountries : String = "",
    val filmImageLink : String = "",
    val isError: Boolean = false,
    val isLoading : Boolean = false
)
