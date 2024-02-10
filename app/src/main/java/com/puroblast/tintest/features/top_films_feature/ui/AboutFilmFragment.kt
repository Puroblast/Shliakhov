package com.puroblast.tintest.features.top_films_feature.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.ImageLoader
import coil.request.ImageRequest
import com.puroblast.tintest.R
import com.puroblast.tintest.databinding.FragmentAboutFilmBinding
import com.puroblast.tintest.features.top_films_feature.presentation.AboutFilmViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AboutFilmFragment : Fragment(R.layout.fragment_about_film) {

    private val binding by viewBinding(FragmentAboutFilmBinding::bind)
    private val aboutFilmViewModel by viewModels<AboutFilmViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val imageLoader = ImageLoader(requireContext())
        val imageRequestBuilder = ImageRequest.Builder(requireContext())
        val filmId = requireArguments().getString("filmId")
        filmId?.let { aboutFilmViewModel.loadFilm(it) }


        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                aboutFilmViewModel.state.collect {
                    binding.filmName.text = it.filmName
                    binding.filmDescription.text = it.filmDescription
                    binding.filmGenres.text = it.filmGenres
                    binding.filmCountries.text = it.filmCountries
                    val request = imageRequestBuilder.data(it.filmImageLink).target { drawable ->
                        binding.filmImage.background = drawable
                    }.build()
                    imageLoader.enqueue(request)
                }
            }
        }

    }

}
