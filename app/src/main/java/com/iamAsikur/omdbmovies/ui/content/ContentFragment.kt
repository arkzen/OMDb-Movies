package com.iamAsikur.omdbmovies.ui.content


import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.iamAsikur.omdbmovies.core.CoreBaseFragment
import com.iamAsikur.omdbmovies.databinding.FragmentContentBinding
import com.iamAsikur.omdbmovies.model.MovieDetailsResponse
import com.iamAsikur.omdbmovies.networking.ResultState
import com.iamAsikur.omdbmovies.utils.Utils
import com.iamAsikur.omdbmovies.utils.Constants
import com.iamAsikur.omdbmovies.viewmodel.MainViewModel
import com.google.android.exoplayer2.MediaItem
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ContentFragment : CoreBaseFragment<FragmentContentBinding>() {


    private val viewModel by viewModels<MainViewModel>()
    private val args: ContentFragmentArgs by navArgs()
    lateinit var player: SimpleExoPlayer

    override fun getViewBinding(): FragmentContentBinding {
        return FragmentContentBinding.inflate(layoutInflater, null, false)
    }

    override fun callInitialApi() {

       val movieId = args.movieItem.imdbID
        if (movieId != null) {
            viewModel.fetchMovieDetails( movieId)
        }
        player = SimpleExoPlayer.Builder(requireContext()).build()
    }
    override fun setupUI() {

        binding.apply {
            btnWatchTrailer.setOnClickListener {
                showProgressBar(true, binding.progressBar)
                playVideo()
            }
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
            data.Poster?.let { Utils.showImage(requireContext(), it,ivMovieCover) }
            tvTitle.text=data.Title
            tvReleaseScreenTime.text="${data.Released}  |  ${data.Runtime}"
            tvGenre.text=data.Genre
            tvRating.text=data.Rated
            tvPlot.text=data.Plot
        }
    }

    fun playVideo(){
        binding.trailerVideo.player = player

        val mediaItem = MediaItem.fromUri(Constants.VIDEO_URL)
        player.setMediaItem(mediaItem)
        player.videoScalingMode = C.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING
        player.prepare()

        player.addListener(object : Player.Listener {
            override fun onIsLoadingChanged(isLoading: Boolean) {
                if (!isLoading) {
                    binding.ivMovieCover.visibility = View.GONE
                    binding.trailerVideo.visibility = View.VISIBLE
                    showProgressBar(false, binding.progressBar)
                }
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        player.release()
    }


}