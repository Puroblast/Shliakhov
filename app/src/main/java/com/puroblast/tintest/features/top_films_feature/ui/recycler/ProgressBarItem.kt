package com.puroblast.tintest.features.top_films_feature.ui.recycler

import android.view.View
import com.mikepenz.fastadapter.items.AbstractItem
import com.puroblast.tintest.R

class ProgressBarItem : AbstractItem<ProgressBarViewHolder>() {
    override val layoutRes: Int = R.layout.progress_bar_item
    override val type: Int = R.id.progressBar

    override fun getViewHolder(v: View): ProgressBarViewHolder {
        return ProgressBarViewHolder(v)
    }
}
