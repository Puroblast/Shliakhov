package com.puroblast.tintest.utils

import com.puroblast.tintest.domain.model.Film

sealed interface AboutFilmState {
    object Loading : AboutFilmState
    class Error(throwable: Throwable) : AboutFilmState
    class Content(val film : Film) : AboutFilmState
}
