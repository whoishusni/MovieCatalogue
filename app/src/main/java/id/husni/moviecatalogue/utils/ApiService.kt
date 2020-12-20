package id.husni.moviecatalogue.utils

import id.husni.moviecatalogue.data.source.local.entity.MoviesEntity
import id.husni.moviecatalogue.data.source.remote.response.MoviesResponse
import id.husni.moviecatalogue.data.source.remote.response.MoviesResult
import id.husni.moviecatalogue.data.source.remote.response.ResultsSeries
import id.husni.moviecatalogue.data.source.remote.response.SeriesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("/3/discover/movie")
    fun getMovies(
        @Query("api_key") api_key: String,
        @Query("language") language: String
    ): Call<MoviesResponse>

    @GET("3/movie/{id}")
    fun getMoviesId(
        @Path("id") id: String,
        @Query("api_key") api_key: String,
        @Query("language") language: String
    ): Call<MoviesResult>

    @GET("/3/discover/tv")
    fun getSeries(
        @Query("api_key") api_key: String,
        @Query("language") language: String
    ): Call<SeriesResponse>

    @GET("3/tv/{id}")
    fun getSeriesById(
        @Path("id") id: String,
        @Query("api_key") api_key: String,
        @Query("language") language: String
    ): Call<ResultsSeries>

}