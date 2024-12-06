package com.iamAsikur.omdbmovies.viewmodel

import androidx.lifecycle.ViewModel
import com.iamAsikur.omdbmovies.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MainRepository,
) : ViewModel() {


}
