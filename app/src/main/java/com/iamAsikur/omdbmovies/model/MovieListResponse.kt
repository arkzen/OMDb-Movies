package com.iamAsikur.omdbmovies.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MovieListResponse(

    @SerializedName("Search") var Search: ArrayList<Search> = arrayListOf(),
    @SerializedName("totalResults") var totalResults: String? = null,
    @SerializedName("Response") var Response: String? = null
)


data class Search(

    @SerializedName("Title") var Title: String? = null,
    @SerializedName("Year") var Year: String? = null,
    @SerializedName("imdbID") var imdbID: String? = null,
    @SerializedName("Type") var Type: String? = null,
    @SerializedName("Poster") var Poster: String? = null

): Serializable