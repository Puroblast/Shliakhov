package com.puroblast.tintest.utils

import com.puroblast.tintest.domain.model.Film

sealed interface AboutFilmFeedState {
    object Loading : AboutFilmFeedState
    class Error(throwable: Throwable) : AboutFilmFeedState
    class Content(val film : Film) : AboutFilmFeedState
}
