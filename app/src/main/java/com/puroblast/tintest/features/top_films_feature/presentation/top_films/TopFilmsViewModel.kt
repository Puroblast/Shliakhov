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
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class TopFilmsViewModel @Inject constructor(
    private val filmsRepository: FilmsRepository
) : ViewModel() {

    private val _state = MutableStateFlow(TopFilmsState())
    val state = _state.asStateFlow()

    init {
        loadTopFilms()
        observeDatabaseChanges()
    }

    fun setFavouriteFilm(film: Film) {
        viewModelScope.launch {
            filmsRepository.setFavouriteFilm(film)
        }
    }

    fun deleteFavouriteFilm(film: Film) {
        viewModelScope.launch {
            filmsRepository.deleteFavouriteFilm(film)
        }
    }

    fun queryChanged(query: String = "") {
        loadTopFilms(query)
    }

    fun changeFilter(filmFilter: FilmFilter) {
        _state.value = _state.value.copy(selectedFilter = filmFilter)
        loadTopFilms()
    }

    private fun observeDatabaseChanges() {
        viewModelScope.launch {
            filmsRepository.observeDatabaseChanges().collectLatest {
                loadTopFilms()
            }
        }
    }

    private fun loadTopFilms(query: String = "") {
        _state.value = _state.value.copy(feedState = TopFilmsFeedState.Loading)
        viewModelScope.launch {
            try {
                val films = filmsRepository.getFilms(query, _state.value.selectedFilter)
                _state.value = _state.value.copy(feedState = TopFilmsFeedState.Content(films))

            } catch (e: Exception) {
                if (e is HttpException || e is IOException) {
                    _state.value =
                        _state.value.copy(feedState = TopFilmsFeedState.Error(NoConnectionException()))
                } else {
                    _state.value = _state.value.copy(feedState = TopFilmsFeedState.Error(e))
                }
            }
        }
    }

}
