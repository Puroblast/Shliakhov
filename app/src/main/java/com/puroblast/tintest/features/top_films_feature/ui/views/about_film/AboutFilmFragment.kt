package com.puroblast.tintest.features.top_films_feature.ui.views.about_film

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.ImageLoader
import coil.request.ImageRequest
import com.puroblast.tintest.R
import com.puroblast.tintest.databinding.FragmentAboutFilmBinding
import com.puroblast.tintest.features.top_films_feature.presentation.about_film.AboutFilmViewModel
import com.puroblast.tintest.utils.NetworkManager.networkAvailable
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AboutFilmFragment : Fragment(R.layout.fragment_about_film) {

    private val binding by viewBinding(FragmentAboutFilmBinding::bind)
    private val aboutFilmViewModel by viewModels<AboutFilmViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (!networkAvailable(requireContext())) {
            binding.scrollView.visibility = View.GONE
            binding.noConnectionView.visibility = View.VISIBLE
        } else {
            render()
        }

        binding.repeatButton.setOnClickListener {
            if (networkAvailable(requireContext())) {
                binding.scrollView.visibility = View.VISIBLE
                binding.noConnectionView.visibility = View.GONE
                render()
            }
        }

        binding.toolBar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun render() {
        val imageLoader = ImageLoader(requireContext())
        val imageRequestBuilder = ImageRequest.Builder(requireContext())
        val filmId = requireArguments().getString("filmId")
        filmId?.let { aboutFilmViewModel.loadFilm(it) }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                aboutFilmViewModel.state.collect {
                    val uiState = it.mapToUiState()
                    binding.filmName.text = uiState.filmName
                    binding.filmDescription.text = uiState.filmDescription
                    binding.filmGenres.text = uiState.filmGenres
                    binding.filmCountries.text = uiState.filmCountries
                    val request = imageRequestBuilder
                        .data(uiState.filmImageLink)
                        .target { drawable ->
                            binding.filmImage.background = drawable
                        }
                        .build()
                    imageLoader.enqueue(request)
                }
            }
        }
    }
}