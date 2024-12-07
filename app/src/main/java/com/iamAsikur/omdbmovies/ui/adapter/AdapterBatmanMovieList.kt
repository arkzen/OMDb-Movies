package com.iamAsikur.omdbmovies.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.iamAsikur.omdbmovies.databinding.ItemMovieBinding
import com.iamAsikur.omdbmovies.model.Search
import com.iamAsikur.omdbmovies.utils.Utils


class AdapterBatmanMovieList(val adapterOnClick: (movieItem: Search) -> Unit) :
    RecyclerView.Adapter<AdapterBatmanMovieList.BatmanMovieViewHolder>() {

    private var movieList = ArrayList<Search>(emptyList())

    class BatmanMovieViewHolder(val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BatmanMovieViewHolder {
        return BatmanMovieViewHolder(
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

    override fun onBindViewHolder(holder: BatmanMovieViewHolder, position: Int) {

        val movieItem = movieList[position]
        holder.binding.apply {
            tvMovieName.text = movieItem.Title
            movieItem.Poster?.let { Utils.showImage(holder.itemView.context, it,ivItemMoviePoster) }
            ivItemMoviePoster
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