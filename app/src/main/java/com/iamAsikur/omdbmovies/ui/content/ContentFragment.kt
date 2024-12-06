package com.iamAsikur.omdbmovies.ui.content

import androidx.fragment.app.viewModels
import com.iamAsikur.omdbmovies.core.CoreBaseFragment
import com.iamAsikur.omdbmovies.databinding.FragmentContentBinding
import com.iamAsikur.omdbmovies.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ContentFragment : CoreBaseFragment<FragmentContentBinding>() {

    companion object {
        fun newInstance() = ContentFragment()
    }

    private val viewModel by viewModels<MainViewModel>()

    override fun getViewBinding(): FragmentContentBinding {
        return FragmentContentBinding.inflate(layoutInflater, null, false)
    }

    override fun setupUI() {


    }


}