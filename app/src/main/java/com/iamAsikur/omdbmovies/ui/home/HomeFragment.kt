package com.iamAsikur.omdbmovies.ui.home

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.iamAsikur.omdbmovies.core.CoreBaseFragment
import com.iamAsikur.omdbmovies.databinding.FragmentHomeBinding
import com.iamAsikur.omdbmovies.networking.ResultState
import com.iamAsikur.omdbmovies.ui.adapter.AdapterBatmanMovieList
import com.iamAsikur.omdbmovies.ui.adapter.AdapterMovieList
import com.iamAsikur.omdbmovies.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : CoreBaseFragment<FragmentHomeBinding>() {


    private val viewModel by viewModels<MainViewModel>()
    lateinit var mMovieListAdapter: AdapterMovieList
    lateinit var mBatmanMovieListAdapter: AdapterBatmanMovieList


    override fun getViewBinding(): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(layoutInflater, null, false)
    }

    override fun setupUI() {
        mMovieListAdapter = AdapterMovieList { movieItem ->
            val action = HomeFragmentDirections.actionHomeFragmentToContentFragment(movieItem)
            findNavController().navigate(action)
        }
        mBatmanMovieListAdapter = AdapterBatmanMovieList { movieItem ->
            val action = HomeFragmentDirections.actionHomeFragmentToContentFragment(movieItem)
            findNavController().navigate(action)
        }
        binding.apply {

            if (rvBatmanMovieList.layoutManager == null) {
                rvBatmanMovieList.layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                rvBatmanMovieList.adapter = mBatmanMovieListAdapter
            }

            if (rvLatestMovieList.layoutManager == null) {
                rvLatestMovieList.layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                rvLatestMovieList.adapter = mMovieListAdapter
            }


            btnBatmanSeeAll.setOnClickListener {
                val action = HomeFragmentDirections.actionHomeFragmentToListingFragment("Batman")
                findNavController().navigate(action)
            }
            btnLatestSeeAll.setOnClickListener {
                val action = HomeFragmentDirections.actionHomeFragmentToListingFragment("Horror")
                findNavController().navigate(action)
            }

        }
    }

    override fun callInitialApi() {
        viewModel.fetchMovieList("horror", 2022, 1)
        viewModel.fetchBatmanMovieList("Batman", 1)
    }

    override fun setupObserver() {

        viewModel.movieList.observe(viewLifecycleOwner) { state ->
            when (state) {
                is ResultState.Loading -> {
                    showProgressBar(true, binding.progressBar)
                }

                is ResultState.Success -> {
                    showProgressBar(false, binding.progressBar)
                    mMovieListAdapter.updateMovieList(state.data.Search)
                }

                is ResultState.Error -> {
                    showProgressBar(false, binding.progressBar)
                    showToastMessage(state.message)
                }
            }
        }

        viewModel.batmanMovieList.observe(viewLifecycleOwner) { state ->
            when (state) {
                is ResultState.Loading -> {
                    showProgressBar(true, binding.progressBar)
                }

                is ResultState.Success -> {
                    showProgressBar(false, binding.progressBar)
                    mBatmanMovieListAdapter.updateMovieList(state.data.Search)

                }

                is ResultState.Error -> {
                    showProgressBar(false, binding.progressBar)
                    showToastMessage(state.message)
                }
            }
        }

    }


}