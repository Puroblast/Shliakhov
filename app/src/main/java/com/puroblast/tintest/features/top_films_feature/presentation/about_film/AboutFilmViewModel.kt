package com.puroblast.tintest.features.top_films_feature.presentation.about_film

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.puroblast.tintest.domain.FilmsRepository
import com.puroblast.tintest.utils.AboutFilmState
import com.puroblast.tintest.utils.NoConnectionException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class AboutFilmViewModel @Inject constructor(
    private val filmsRepository: FilmsRepository
) : ViewModel() {

    private val _state = MutableStateFlow(AboutFilmState())
    val state = _state.asStateFlow()

    fun loadFilm(id: String) {
        viewModelScope.launch {
            try {
                val film = filmsRepository.getFilm(id)
                _state.value = _state.value.copy(aboutFilmState = AboutFilmState.Content(film))
            } catch (e: Exception) {
                if (e is HttpException || e is IOException) {
                    _state.value = _state.value.copy(
                        aboutFilmState = AboutFilmState.Error(
                            NoConnectionException()
                        )
                    )
                } else {
                    _state.value = _state.value.copy(
                        aboutFilmState = AboutFilmState.Error(e)
                    )
                }

            }

        }
    }
}
