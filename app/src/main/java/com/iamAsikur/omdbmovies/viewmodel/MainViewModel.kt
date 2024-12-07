package com.iamAsikur.omdbmovies.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iamAsikur.omdbmovies.model.MovieDetailsResponse
import com.iamAsikur.omdbmovies.model.MovieListResponse
import com.iamAsikur.omdbmovies.networking.ResultState
import com.iamAsikur.omdbmovies.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MainRepository,
) : ViewModel() {

    private val _movieList = MutableLiveData<ResultState<MovieListResponse>>()
    val movieList: LiveData<ResultState<MovieListResponse>> = _movieList


   private val _batmanMovieList = MutableLiveData<ResultState<MovieListResponse>>()
    val batmanMovieList: LiveData<ResultState<MovieListResponse>> = _batmanMovieList


    private val _movieDetails = MutableLiveData<ResultState<MovieDetailsResponse>>()
    val movieDetails: LiveData<ResultState<MovieDetailsResponse>> = _movieDetails


    fun fetchMovieList(movieType: String,year:Int,page:Int) {
        viewModelScope.launch {
            _movieList.value = ResultState.Loading
            try {
                val response = repository.getMovieList(movieType,year,page)
                _movieList.value = ResultState.Success(response)
            } catch (e: Exception) {
                _movieList.value = ResultState.Error(e.localizedMessage ?: "An error occurred")
            }
        }
    }

    fun fetchBatmanMovieList(movieType: String,page:Int) {
        viewModelScope.launch {
            _movieList.value = ResultState.Loading
            try {
                val response = repository.getMovieList(movieType,page)
                _batmanMovieList.value = ResultState.Success(response)
            } catch (e: Exception) {
                _batmanMovieList.value = ResultState.Error(e.localizedMessage ?: "An error occurred")
            }
        }
    }


    fun fetchMovieDetails(movieId: String) {
        viewModelScope.launch {
            _movieDetails.value = ResultState.Loading
            try {
                val response = repository.getMovieDetails(movieId)
                _movieDetails.value = ResultState.Success(response)
            } catch (e: Exception) {
                _movieDetails.value = ResultState.Error(e.localizedMessage ?: "An error occurred")
            }
        }
    }
}
