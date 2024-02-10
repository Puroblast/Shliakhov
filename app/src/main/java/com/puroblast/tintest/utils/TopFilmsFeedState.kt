package com.puroblast.tintest.utils

import com.puroblast.tintest.domain.model.Film

sealed interface TopFilmsFeedState {
    object Loading : TopFilmsFeedState
    object EmptyQuery : TopFilmsFeedState
    class Error(throwable: Throwable) : TopFilmsFeedState
    class Content(val films: List<Film>) : TopFilmsFeedState
}
