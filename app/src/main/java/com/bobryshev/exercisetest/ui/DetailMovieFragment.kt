package com.bobryshev.exercisetest.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bobryshev.exercisetest.BuildConfig
import com.bobryshev.exercisetest.R
import com.bobryshev.exercisetest.data.domain.moviedetail.MovieDetail
import com.bobryshev.exercisetest.databinding.FragmentImdbDetailMoviewBinding
import com.bobryshev.exercisetest.ui.viewmodel.DetailMovieViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailMovieFragment : Fragment() {

    private lateinit var layout: FragmentImdbDetailMoviewBinding

    private var id: String? = ""

    private val viewModel: DetailMovieViewModel by viewModels()
    private val movieDetailObserver = Observer<MovieDetail> { setupData(it) }
    private val errorObserver = Observer<String> { Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        layout = FragmentImdbDetailMoviewBinding.inflate(inflater)
        return layout.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            val safeArgs = DetailMovieFragmentArgs.fromBundle(it)
            id = safeArgs.StringArgsKeyMovieId
        }

        viewModel.getMovieDetail(id!!)
        viewModel.detailMovieLiveData.observe(viewLifecycleOwner, movieDetailObserver)
        viewModel.errorLiveData.observe(viewLifecycleOwner, errorObserver)
    }

    private fun setupData(movieDetail: MovieDetail) {
        layout.tvTitle.text = movieDetail.title
        layout.tvPopularity.text = getString(R.string.popularity).format(movieDetail.popularity)
        Picasso.get().load(BuildConfig.IMAGE_URL + movieDetail.posterPath).into(layout.ivPoster)
    }
}