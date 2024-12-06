package com.iamAsikur.omdbmovies.ui.home

import androidx.fragment.app.viewModels
import com.iamAsikur.omdbmovies.core.CoreBaseFragment
import com.iamAsikur.omdbmovies.databinding.FragmentHomeBinding
import com.iamAsikur.omdbmovies.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : CoreBaseFragment<FragmentHomeBinding>() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private val viewModel by viewModels<MainViewModel>()

    override fun getViewBinding(): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(layoutInflater, null, false)
    }

    override fun setupUI() {


    }


}