package com.puroblast.tintest.features.top_films_feature.ui.recycler

import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.mikepenz.fastadapter.FastAdapter
import com.puroblast.tintest.databinding.FilmItemBinding

class FilmsViewHolder(view: View): FastAdapter.ViewHolder<FilmItem>(view) {

    val binding by viewBinding(FilmItemBinding::bind)
    override fun bindView(item: FilmItem, payloads: List<Any>) {
        with(binding) {
            previewImage.load(item.imageLink)
            filmName.text = item.name
            filmGenre.text = item.genres.map { it.genre }.first()
            filmYear.text = "(${item.year})"
        }

    }

    override fun unbindView(item: FilmItem) {
        with(binding) {
            filmName.text = null
            filmGenre.text = null
            filmYear.text = null
        }
    }
}