package com.iamAsikur.omdbmovies.utils

import android.content.Context
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AppNavHostFragment : NavHostFragment() {

    @Inject
    lateinit var appFragmentFactory: AppFragmentFactory

    override fun onAttach(context: Context) {
        super.onAttach(context)
        childFragmentManager.fragmentFactory = appFragmentFactory
    }
}