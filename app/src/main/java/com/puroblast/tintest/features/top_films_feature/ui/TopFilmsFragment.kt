package com.puroblast.tintest.features.top_films_feature.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.mikepenz.fastadapter.adapters.FastItemAdapter
import com.mikepenz.fastadapter.diff.FastAdapterDiffUtil
import com.mikepenz.fastadapter.select.selectExtension
import com.puroblast.tintest.R
import com.puroblast.tintest.databinding.FragmentTopFilmsBinding
import com.puroblast.tintest.features.top_films_feature.presentation.FilmItem
import com.puroblast.tintest.features.top_films_feature.presentation.TopFilmsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TopFilmsFragment : Fragment(R.layout.fragment_top_films) {

    private val binding by viewBinding(FragmentTopFilmsBinding::bind)
    private val filmViewModel by viewModels<TopFilmsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val filmItemAdapter = FastItemAdapter<FilmItem>()
        filmItemAdapter.selectExtension {
            selectOnLongClick = true
        }

        binding.filmsRecycler.layoutManager = LinearLayoutManager(context)
        binding.filmsRecycler.adapter = filmItemAdapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                filmViewModel.state.collect {
                    filmItem ->
                    val result = FastAdapterDiffUtil.calculateDiff(filmItemAdapter.itemAdapter , filmItem)
                    FastAdapterDiffUtil[filmItemAdapter.itemAdapter] = result
                }
            }
        }

        binding.toolBar.setOnMenuItemClickListener {menuItem ->
            when(menuItem.itemId) {
                R.id.searchIcon -> {
                    (menuItem.actionView as SearchView).setOnQueryTextListener(object : SearchView.OnQueryTextListener {

                        override fun onQueryTextSubmit(query: String?): Boolean {
                            filmItemAdapter.filter(query)
                            filmItemAdapter.itemFilter.filterPredicate = {
                                    item: FilmItem, constraint: CharSequence? ->
                                item.name.contains(constraint.toString() , ignoreCase = true)
                            }
                            return true
                        }

                        override fun onQueryTextChange(newText: String?): Boolean {
                            filmItemAdapter.filter(newText)
                            filmItemAdapter.itemFilter.filterPredicate = {
                                    item: FilmItem, constraint: CharSequence? ->
                                item.name.contains(constraint.toString() , ignoreCase = true)
                            }
                            return true
                        }
                    })
                    true
                } else -> {
                    true
                }
            }
        }
    }


}
