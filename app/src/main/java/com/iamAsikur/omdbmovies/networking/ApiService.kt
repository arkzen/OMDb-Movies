package com.iamAsikur.omdbmovies.networking

import com.iamAsikur.omdbmovies.BuildConfig
import com.iamAsikur.omdbmovies.model.MovieDetailsResponse
import com.iamAsikur.omdbmovies.model.MovieListResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

    @GET("/")
    suspend fun getMovieList(
        @Query("s") movieType: String,
        @Query("page") page: Int,
        @Query("apikey") apiKey: String = BuildConfig.API_KEY
    ): MovieListResponse

    @GET("/")
    suspend fun getMovieList(
        @Query("s") movieType: String,
        @Query("y") y: Int,
        @Query("page") page: Int,
        @Query("apikey") apiKey: String = BuildConfig.API_KEY
    ): MovieListResponse

    @GET("/")
    suspend fun getMovieDetails(
        @Query("i") movieId: String,
        @Query("apikey") apiKey: String = BuildConfig.API_KEY
    ): MovieDetailsResponse
}