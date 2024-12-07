package com.iamAsikur.omdbmovies.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.iamAsikur.omdbmovies.ui.content.ContentFragment
import com.iamAsikur.omdbmovies.ui.home.HomeFragment
import com.iamAsikur.omdbmovies.ui.listing.ListingFragment
import javax.inject.Inject

class AppFragmentFactory @Inject constructor(

) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {
            HomeFragment::class.java.name -> {
                HomeFragment()
            }

            ListingFragment::class.java.name -> {
                ListingFragment()
            }

            ContentFragment::class.java.name -> {
                ContentFragment()
            }

            else -> super.instantiate(classLoader, className)
        }
    }
}