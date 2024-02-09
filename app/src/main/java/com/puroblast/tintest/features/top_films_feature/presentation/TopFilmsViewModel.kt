package com.puroblast.tintest.features.top_films_feature.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.puroblast.tintest.domain.FilmsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopFilmsViewModel @Inject constructor(
    private val filmsRepository: FilmsRepository
) : ViewModel() {

    private val _state = MutableStateFlow(emptyList<FilmItem>())
    val state = _state.asStateFlow()

    init {
        loadTopFilms()
    }

    private fun loadTopFilms() {
        viewModelScope.launch {
            val films = filmsRepository.getTopFilms().films
            _state.value = films.map {
                FilmItem(
                    it.filmId,
                    it.nameRu,
                    it.genres,
                    it.year.toString(),
                    it.posterUrl
                )
            }
        }
    }

}
