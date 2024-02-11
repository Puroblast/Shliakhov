package com.puroblast.tintest

import com.puroblast.tintest.domain.model.Country
import com.puroblast.tintest.domain.model.Film
import com.puroblast.tintest.domain.model.Genre
import com.puroblast.tintest.features.top_films_feature.ui.recycler.films.FilmItem
import com.puroblast.tintest.features.top_films_feature.ui.recycler.no_connection.NoConnectionItem
import com.puroblast.tintest.features.top_films_feature.ui.recycler.progress_bar.ProgressBarItem
import com.puroblast.tintest.features.top_films_feature.ui.views.about_film.AboutFilmUiState
import com.puroblast.tintest.features.top_films_feature.ui.views.top_films.TopFilmsUiState
import com.puroblast.tintest.utils.AboutFilmState
import com.puroblast.tintest.utils.NoConnectionException
import com.puroblast.tintest.utils.StateToUiStateMapper
import com.puroblast.tintest.utils.TopFilmsFeedState
import org.junit.Assert.assertEquals
import org.junit.Test

class UiMapperTest {

    val stateToUiStateMapper = StateToUiStateMapper()
    val noConnectionException = NoConnectionException()
    val film = Film(
        filmId = 1 ,
        nameRu = "test" ,
        posterUrl = "test" ,
        posterUrlPreview  = "test",
        year = 0 ,
        genres = listOf(Genre(genre = "test")) ,
        countries = listOf(Country(country = "test")) ,
        description  = null,
        isFavourite  = false,
        position = null
    )
    @Test
    fun topFilmsUiStateMapper_FeedStateLoading_ReturnsTrue() {
        val rightAnswer = TopFilmsUiState(progressBarItem = ProgressBarItem())
        assertEquals(
            rightAnswer,
            stateToUiStateMapper.mapTopFilmStateToUiState(TopFilmsFeedState.Loading)
        )
    }

    @Test
    fun topFilmsUiStateMapper_FeedStateError_ReturnsTrue() {
        val rightAnswer = TopFilmsUiState(errorItem = NoConnectionItem())
        assertEquals(
            rightAnswer,
            stateToUiStateMapper.mapTopFilmStateToUiState(TopFilmsFeedState.Error(noConnectionException))
        )
    }
    @Test
    fun topFilmsUiStateMapper_FeedStateContent_ReturnsTrue() {
        val filmItems = listOf(FilmItem(
            film = film,
            genres = film.genres,
            id = film.filmId,
            imageLink = film.posterUrl,
            name = film.nameRu,
            year = film.year.toString()
        ))
        val rightAnswer = TopFilmsUiState(filmItems = filmItems)
        assertEquals(
            rightAnswer,
            stateToUiStateMapper.mapTopFilmStateToUiState(TopFilmsFeedState.Content(listOf(film)))
        )
    }

    @Test
    fun aboutFilmUiStateMapper_StateLoading_ReturnsTrue() {
        val rightAnswer = AboutFilmUiState(isLoading = true , isError = false)
        assertEquals(
            rightAnswer,
            stateToUiStateMapper.mapAboutFilmStateToUiState(AboutFilmState.Loading)
        )
    }

    @Test
    fun aboutFilmUiStateMapper_StateError_ReturnsTrue() {
        val rightAnswer = AboutFilmUiState(isError = true , isLoading = false)
        assertEquals(
            rightAnswer,
            stateToUiStateMapper.mapAboutFilmStateToUiState(AboutFilmState.Error(noConnectionException))
        )
    }
    @Test
    fun aboutFilmUiStateMapper_StateContent_ReturnsTrue() {
        val rightAnswer = AboutFilmUiState(
            filmName = film.nameRu,
            filmCountries = film.countries.map { it.country }.joinToString(", "),
            filmGenres = film.genres.map { it.genre }.joinToString(", "),
            filmDescription = film.description,
            filmImageLink = film.posterUrl
        )
        assertEquals(
            rightAnswer,
            stateToUiStateMapper.mapAboutFilmStateToUiState(AboutFilmState.Content(film))
        )
    }
}
