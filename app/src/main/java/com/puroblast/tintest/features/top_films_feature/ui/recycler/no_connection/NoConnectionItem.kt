package com.puroblast.tintest.features.top_films_feature.ui.recycler.no_connection

import android.view.View
import com.mikepenz.fastadapter.items.AbstractItem
import com.puroblast.tintest.R

class NoConnectionItem: AbstractItem<NoConnectionViewHolder>() {

    override val layoutRes: Int = R.layout.no_internet_item
    override val type: Int = R.id.noConnectionView

    override fun getViewHolder(v: View): NoConnectionViewHolder {
        return NoConnectionViewHolder(v)
    }
}
