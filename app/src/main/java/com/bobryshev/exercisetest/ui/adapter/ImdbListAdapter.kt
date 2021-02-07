package com.bobryshev.exercisetest.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bobryshev.exercisetest.BuildConfig
import com.bobryshev.exercisetest.R
import com.bobryshev.exercisetest.data.domain.movielist.MovieListItem
import com.bobryshev.exercisetest.databinding.ItemImdbBinding
import com.squareup.picasso.Picasso

class ImdbListAdapter(private val context: Context, private val onClick: (id: String) -> Unit) : PagingDataAdapter<MovieListItem, ImdbListAdapter.ImdbListViewHolder>(PopularMovieComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImdbListViewHolder =
        ImdbListViewHolder(ItemImdbBinding.inflate(LayoutInflater.from(context), parent, false))

    override fun onBindViewHolder(holder: ImdbListViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class ImdbListViewHolder(val layout: ItemImdbBinding) : RecyclerView.ViewHolder(layout.root) {

        fun bind(item: MovieListItem?) {
            item?.let {
                layout.tvTitle.text = item.title
                layout.tvPopularity.text = context.getString(R.string.popularity).format(item.popularity)
                Picasso.get().load(BuildConfig.IMAGE_URL + item.posterPath)
                    .into(layout.ivPoster)

                layout.root.setOnClickListener {
                    onClick(item.id)
                }
            }
        }
    }

    companion object {
        private val PopularMovieComparator = object : DiffUtil.ItemCallback<MovieListItem>() {
            override fun areItemsTheSame(oldItem: MovieListItem, newItem: MovieListItem): Boolean {
                return oldItem.id != newItem.id
            }

            override fun areContentsTheSame(oldItem: MovieListItem, newItem: MovieListItem): Boolean =
                oldItem == newItem
        }
    }
}