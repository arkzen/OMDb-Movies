package com.iamAsikur.omdbmovies.repository


import com.iamAsikur.omdbmovies.networking.ApiService
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun getMovieList(movieType: String,page:Int) = apiService.getMovieList(movieType,page)
    suspend fun getMovieList(movieType: String,year:Int,page:Int) = apiService.getMovieList(movieType,year,page)
    suspend fun getMovieDetails(movieId: String) = apiService.getMovieDetails(movieId)
}