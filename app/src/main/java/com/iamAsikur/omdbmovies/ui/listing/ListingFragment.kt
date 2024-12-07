package com.iamAsikur.omdbmovies.ui.listing
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.iamAsikur.omdbmovies.core.CoreBaseFragment
import com.iamAsikur.omdbmovies.databinding.FragmentListingBinding
import com.iamAsikur.omdbmovies.networking.ResultState
import com.iamAsikur.omdbmovies.ui.adapter.AdapterMovieList
import com.iamAsikur.omdbmovies.ui.home.HomeFragmentDirections
import com.iamAsikur.omdbmovies.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ListingFragment : CoreBaseFragment<FragmentListingBinding>() {

    private val viewModel by viewModels<MainViewModel>()
    private val args: ListingFragmentArgs by navArgs()
    lateinit var mMovieListAdapter: AdapterMovieList
    private var page=1
    private var nextPageState=true
    private var searchQuery=""

    override fun getViewBinding(): FragmentListingBinding {
        return FragmentListingBinding.inflate(layoutInflater, null, false)
    }

    override fun callInitialApi() {

        searchQuery = args.searchQuery
        viewModel.fetchBatmanMovieList(searchQuery, page)
    }
    override fun setupUI() {
        mMovieListAdapter = AdapterMovieList { movieItem ->
            val action = ListingFragmentDirections.actionListingFragmentToContentFragment(movieItem)
            findNavController().navigate(action)
        }

      binding.apply {
          if (rvMovieList.layoutManager == null) {
              val spanCount = 2
              rvMovieList.layoutManager =
                  GridLayoutManager(requireContext(), spanCount, GridLayoutManager.VERTICAL, false)
              rvMovieList.adapter = mMovieListAdapter
          }


          rvMovieList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
              override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                  super.onScrolled(recyclerView, dx, dy)

                  if (dy > 0) {
                      val layoutManager = recyclerView.layoutManager as GridLayoutManager

                      val visibleItemCount = layoutManager.childCount
                      val totalItemCount = layoutManager.itemCount
                      val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                      if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount &&
                          firstVisibleItemPosition >= 0) {
                         if (nextPageState){
                             viewModel.fetchBatmanMovieList(searchQuery, page++)
                         }
                      }
                  }
              }
          })
      }

    }

    override fun setupObserver() {

        viewModel.batmanMovieList.observe(viewLifecycleOwner) { state ->
            when (state) {
                is ResultState.Loading -> {
                    showProgressBar(true, binding.progressBar)
                }

                is ResultState.Success -> {
                    showProgressBar(false, binding.progressBar)
                    if (state.data.Response!=="True"){
                        nextPageState=false
                    }
                    mMovieListAdapter.updateMovieList(state.data.Search)

                }

                is ResultState.Error -> {
                    showProgressBar(false, binding.progressBar)
                    showToastMessage(state.message)
                }
            }
        }
    }


}