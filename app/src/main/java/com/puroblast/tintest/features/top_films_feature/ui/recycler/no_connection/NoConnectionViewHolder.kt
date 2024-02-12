package com.puroblast.tintest.features.top_films_feature.ui.recycler.no_connection

import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mikepenz.fastadapter.FastAdapter
import com.puroblast.tintest.databinding.NoInternetItemBinding

class NoConnectionViewHolder(view : View) : FastAdapter.ViewHolder<NoConnectionItem>(view) {

    val binding by viewBinding(NoInternetItemBinding::bind)
    override fun bindView(item: NoConnectionItem, payloads: List<Any>) {

    }

    override fun unbindView(item: NoConnectionItem) {

    }
}
