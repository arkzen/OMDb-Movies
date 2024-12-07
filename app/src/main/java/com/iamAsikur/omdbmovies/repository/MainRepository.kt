package com.iamAsikur.omdbmovies.repository


import com.iamAsikur.omdbmovies.networking.ApiService
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun getMovieList(movieType: String) = apiService.getMovieList(movieType)
    suspend fun getMovieDetails(movieId: String) = apiService.getMovieDetails(movieId)
}