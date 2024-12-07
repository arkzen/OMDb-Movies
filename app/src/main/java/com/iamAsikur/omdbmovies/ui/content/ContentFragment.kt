package com.iamAsikur.omdbmovies.ui.content

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.iamAsikur.omdbmovies.core.CoreBaseFragment
import com.iamAsikur.omdbmovies.databinding.FragmentContentBinding
import com.iamAsikur.omdbmovies.model.MovieDetailsResponse
import com.iamAsikur.omdbmovies.networking.ResultState
import com.iamAsikur.omdbmovies.ui.adapter.AdapterMovieList
import com.iamAsikur.omdbmovies.ui.listing.ListingFragmentArgs
import com.iamAsikur.omdbmovies.ui.listing.ListingFragmentDirections
import com.iamAsikur.omdbmovies.utils.Utils
import com.iamAsikur.omdbmovies.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ContentFragment : CoreBaseFragment<FragmentContentBinding>() {


    private val viewModel by viewModels<MainViewModel>()
    private val args: ContentFragmentArgs by navArgs()
    override fun getViewBinding(): FragmentContentBinding {
        return FragmentContentBinding.inflate(layoutInflater, null, false)
    }

    override fun callInitialApi() {

       val movieId = args.movieItem.imdbID
        if (movieId != null) {
            viewModel.fetchMovieDetails( movieId)
        }
    }
    override fun setupUI() {

        binding.apply {


             }

    }

    override fun setupObserver() {

        viewModel.movieDetails.observe(viewLifecycleOwner) { state ->
            when (state) {
                is ResultState.Loading -> {
                    showProgressBar(true, binding.progressBar)
                }

                is ResultState.Success -> {
                    showProgressBar(false, binding.progressBar)
                    setUiData(state.data)
                }

                is ResultState.Error -> {
                    showProgressBar(false, binding.progressBar)
                    showToastMessage(state.message)
                }
            }
        }
    }



    fun setUiData(data: MovieDetailsResponse){

        binding.apply {
            data.Poster?.let { Utils.showImage(requireContext(), it,ivCourseCover) }
            tvTitle.text=data.Title
            tvReleaseScreenTime.text="${data.Released}  |  ${data.Runtime}"
            tvGenre.text=data.Genre
            tvRating.text=data.Rated
            tvPlot.text=data.Plot
        }
    }

}