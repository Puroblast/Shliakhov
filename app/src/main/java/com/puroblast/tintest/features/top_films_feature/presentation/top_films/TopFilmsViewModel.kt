package com.puroblast.tintest.features.top_films_feature.presentation.top_films

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.puroblast.tintest.domain.FilmsRepository
import com.puroblast.tintest.domain.model.Film
import com.puroblast.tintest.utils.TopFilmsFeedState
import com.puroblast.tintest.utils.FilmFilter
import com.puroblast.tintest.utils.NoConnectionException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopFilmsViewModel @Inject constructor(
    private val filmsRepository: FilmsRepository
) : ViewModel() {

    private val _state = MutableStateFlow(TopFilmsState())
    val state = _state.asStateFlow()

    init {
        loadTopFilms("" , FilmFilter.POPULAR)
    }
    private fun loadTopFilms(query : String, filmFilter: FilmFilter = _state.value.selectedFilter) {
        _state.value = _state.value.copy(feedState = TopFilmsFeedState.Loading)
        viewModelScope.launch {
            try {
                filmsRepository.observeFilms(query,filmFilter).collect {
                        films : List<Film> ->
                    _state.value = _state.value.copy(feedState = TopFilmsFeedState.Content(films))
                }
            } catch (e : Exception) {
                _state.value = _state.value.copy(feedState = TopFilmsFeedState.Error(NoConnectionException()))
            }

        }
    }

    fun queryChanged(query: String = "" , filmFilter: FilmFilter) {
        loadTopFilms(query,filmFilter)
    }

}
