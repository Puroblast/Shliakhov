package com.puroblast.tintest.features.top_films_feature.presentation

import android.view.View
import com.mikepenz.fastadapter.items.AbstractItem
import com.puroblast.tintest.R
import com.puroblast.tintest.domain.model.Genre

class FilmItem(
    private val id : Int,
    val name : String,
    val genres : List<Genre>,
    val year : String,
    val imageLink : String

) : AbstractItem<FilmsViewHolder>() {
    override val layoutRes: Int
        get() = R.layout.film_item
    override val type: Int
        get() = R.id.filmItem

    override var identifier: Long = id.toLong()

    override fun getViewHolder(v: View): FilmsViewHolder {
        return FilmsViewHolder(v)
    }

}
