package com.iamAsikur.omdbmovies.ui.listing
import androidx.fragment.app.viewModels
import com.iamAsikur.omdbmovies.core.CoreBaseFragment
import com.iamAsikur.omdbmovies.databinding.FragmentListingBinding
import com.iamAsikur.omdbmovies.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ListingFragment : CoreBaseFragment<FragmentListingBinding>() {

    companion object {
        fun newInstance() = ListingFragment()
    }

    private val viewModel by viewModels<MainViewModel>()

    override fun getViewBinding(): FragmentListingBinding {
        return FragmentListingBinding.inflate(layoutInflater, null, false)
    }

    override fun setupUI() {


    }


}