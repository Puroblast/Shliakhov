package com.puroblast.tintest.features.top_films_feature.ui.recycler.films

import android.view.View
import androidx.core.view.isVisible
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.mikepenz.fastadapter.FastAdapter
import com.puroblast.tintest.R
import com.puroblast.tintest.databinding.FilmItemBinding

class FilmsViewHolder(val view: View): FastAdapter.ViewHolder<FilmItem>(view) {

    val binding by viewBinding(FilmItemBinding::bind)
    override fun bindView(item: FilmItem, payloads: List<Any>) {
        with(binding) {
            previewImage.load(item.imageLink)
            filmName.text = item.name
            filmGenre.text = item.genres.map { it.genre }.first().replaceFirstChar { it.uppercaseChar() }
            filmYear.text = view.context.getString(R.string.year , item.year)
            favouriteIcon.isVisible = item.isFavourite
        }

    }

    override fun unbindView(item: FilmItem) {
        with(binding) {
            filmName.text = null
            filmGenre.text = null
            filmYear.text = null
            favouriteIcon.isVisible = false
        }
    }
}
