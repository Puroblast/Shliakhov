package com.puroblast.tintest.features.top_films_feature.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.GenericFastAdapter
import com.mikepenz.fastadapter.adapters.FastItemAdapter
import com.mikepenz.fastadapter.diff.FastAdapterDiffUtil
import com.mikepenz.fastadapter.listeners.ClickEventHook
import com.mikepenz.fastadapter.select.selectExtension
import com.puroblast.tintest.R
import com.puroblast.tintest.databinding.FragmentTopFilmsBinding
import com.puroblast.tintest.features.top_films_feature.ui.recycler.FilmItem
import com.puroblast.tintest.features.top_films_feature.presentation.top_films.TopFilmsViewModel
import com.puroblast.tintest.features.top_films_feature.ui.recycler.FilmsViewHolder
import com.puroblast.tintest.features.top_films_feature.ui.recycler.NoConnectionItem
import com.puroblast.tintest.features.top_films_feature.ui.recycler.NoConnectionViewHolder
import com.puroblast.tintest.features.top_films_feature.ui.recycler.ProgressBarItem
import com.puroblast.tintest.utils.FilmFilter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TopFilmsFragment : Fragment(R.layout.fragment_top_films) {

    private val binding by viewBinding(FragmentTopFilmsBinding::bind)
    private val filmViewModel by viewModels<TopFilmsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        render()
    }

    private fun render() {
        val filmItemAdapter = FastItemAdapter<FilmItem>()
        val noConnectionItemAdapter = FastItemAdapter<NoConnectionItem>()
        val progressBarItemAdapter = FastItemAdapter<ProgressBarItem>()
        filmItemAdapter.selectExtension {
            selectOnLongClick = true
        }

        val fastAdapter: GenericFastAdapter = FastAdapter.with(
            listOf(
                filmItemAdapter, noConnectionItemAdapter, progressBarItemAdapter
            )
        )
        binding.filmsRecycler.layoutManager = LinearLayoutManager(context)
        binding.filmsRecycler.adapter = fastAdapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                filmViewModel.state.collect { state ->
                    val uiState = state.mapToUiState()
                    if (uiState.progressBarItem != null) {
                        progressBarItemAdapter.set(listOf(uiState.progressBarItem))
                        noConnectionItemAdapter.clear()
                    } else if (uiState.errorItem != null) {
                        noConnectionItemAdapter.set(listOf(uiState.errorItem))
                        progressBarItemAdapter.clear()
                    } else {
                        val result = FastAdapterDiffUtil.calculateDiff(
                            filmItemAdapter.itemAdapter, uiState.filmItems
                        )
                        FastAdapterDiffUtil[filmItemAdapter.itemAdapter] = result
                        progressBarItemAdapter.clear()
                    }


                }
            }
        }

        fastAdapter.addEventHook(object : ClickEventHook<FilmItem>() {
            override fun onBind(viewHolder: RecyclerView.ViewHolder): View? {
                return if (viewHolder is FilmsViewHolder) {
                    viewHolder.binding.filmItem
                } else {
                    null
                }
            }

            override fun onClick(
                v: View, position: Int, fastAdapter: FastAdapter<FilmItem>, item: FilmItem
            ) {
                val bundle = bundleOf()
                bundle.putString("filmId", item.id.toString())
                findNavController().navigate(
                    R.id.action_topFilmsScreen_to_aboutFilmFragment, bundle
                )
            }
        })

        fastAdapter.addEventHook(object : ClickEventHook<NoConnectionItem>() {
            override fun onBind(viewHolder: RecyclerView.ViewHolder): View? {
                return if (viewHolder is NoConnectionViewHolder) {
                    viewHolder.binding.repeatButton
                } else {
                    null
                }
            }

            override fun onClick(
                v: View,
                position: Int,
                fastAdapter: FastAdapter<NoConnectionItem>,
                item: NoConnectionItem
            ) {
                filmViewModel.queryChanged("", FilmFilter.POPULAR)
            }
        })



        binding.toolBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.searchIcon -> {
                    (menuItem.actionView as SearchView).setOnQueryTextListener(object :
                        SearchView.OnQueryTextListener {

                        override fun onQueryTextSubmit(query: String?): Boolean {
                            return true
                        }

                        override fun onQueryTextChange(newText: String?): Boolean {
                            filmViewModel.queryChanged(newText ?: "", FilmFilter.POPULAR)
                            return true
                        }
                    })
                    true
                }

                else -> {
                    true
                }
            }
        }
    }

}
