package com.bobryshev.exercisetest.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bobryshev.exercisetest.Util
import com.bobryshev.exercisetest.databinding.FragmentImdbListBinding
import com.bobryshev.exercisetest.ui.adapter.ImdbListAdapter
import com.bobryshev.exercisetest.ui.viewmodel.PopularMovieViewModel
import com.bobryshev.exercisetest.ui.viewmodel.SearchMovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : Fragment() {

    private lateinit var layour: FragmentImdbListBinding
    private lateinit var mAdapter: ImdbListAdapter

    private var searchJob: Job? = null
    private var popularMovieJob: Job? = null

    private val popularMovieViewModel: PopularMovieViewModel by viewModels()
    private val searchMovieViewModel: SearchMovieViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        layour = FragmentImdbListBinding.inflate(inflater)
        return layour.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        setupSearchField()

        layour.refresh.setOnRefreshListener { mAdapter.refresh() }

        getPopularMovieList()

    }

    private fun setupAdapter() {
        mAdapter = ImdbListAdapter(requireContext()) {
            findNavController().navigate(MainFragmentDirections.actionImdbListFragmentToImdbDetailMovieFragment(it))
        }

        layour.rvImdbList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = mAdapter
            addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))
        }

        lifecycleScope.launch {
            mAdapter.loadStateFlow
                .distinctUntilChangedBy { it.refresh }
                .filter { it.refresh is LoadState.NotLoading }
                .collect { layour.rvImdbList.scrollToPosition(0) }
        }
    }

    private fun setupSearchField() {
        layour.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrBlank()) {
                    getPopularMovieList()
                } else {
                    search(newText)
                }
                return false
            }

        })

        layour.searchView.setOnCloseListener {
            getPopularMovieList()
            false
        }

        layour.searchView.setOnQueryTextFocusChangeListener { v, hasFocus ->
            if (hasFocus.not()) {
                Util.hideKeyboardFrom(requireContext(), v)
            }
        }
    }

    private fun getPopularMovieList() {
        searchJob?.cancel()
        popularMovieJob?.cancel()
        popularMovieJob = lifecycleScope.launch {
            popularMovieViewModel.getPopularMovieList()
                .collectLatest {
                    mAdapter.submitData(it)
                }
        }
    }

    private fun search(query: String) {
        popularMovieJob?.cancel()
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            delay(400)
            searchMovieViewModel.searchMovie(query)
                .collect {
                    mAdapter.submitData(it)
                }
        }
    }
}