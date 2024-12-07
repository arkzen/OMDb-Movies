package com.iamAsikur.omdbmovies.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.iamAsikur.omdbmovies.databinding.ItemMovieBinding
import com.iamAsikur.omdbmovies.model.Search


class AdapterMovieList(val adapterOnClick: (movieItem: Search) -> Unit) :
    RecyclerView.Adapter<AdapterMovieList.MovieViewHolder>() {

    private var movieList = ArrayList<Search>(emptyList())

    class MovieViewHolder(val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

        val movieItem = movieList[position]
        holder.binding.apply {
            tvMovieName.text = movieItem.Title
            root.setOnClickListener {
                adapterOnClick.invoke(movieItem)
            }

        }

    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateMovieList(newMovieList: List<Search>) {
        for (item in newMovieList) {
            movieList.add(item)
        }
        notifyDataSetChanged()
    }

}