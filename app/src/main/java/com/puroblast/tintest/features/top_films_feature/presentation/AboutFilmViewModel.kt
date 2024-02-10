package com.puroblast.tintest.features.top_films_feature.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.puroblast.tintest.domain.FilmsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AboutFilmViewModel @Inject constructor(
    private val filmsRepository: FilmsRepository
) : ViewModel() {

    private val _state = MutableStateFlow(AboutFilmUiState())
    val state = _state.asStateFlow()

    fun loadFilm(id: String) {
        viewModelScope.launch {
            val film = filmsRepository.getFilm(id)
            _state.value = AboutFilmUiState(
                film.nameRu,
                film.description,
                film.genres.joinToString(",") { it.genre },
                film.countries.joinToString(",") { it.country },
                film.posterUrl
            )
        }
    }
}
