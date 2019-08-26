package com.murat.movielist.service

import com.murat.movielist.service.response.*
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TmdbAPI  {
    @GET("movie/{type}")
    fun getMovies(
        @Path("type") TYPE: String, @Query("api_key") API_KEY: String, @Query("language") LANGUAGE: String, @Query(
            "page"
        ) PAGE: Int
    ): Observable<TMDBResponse>


    @GET("search/movie")
    fun searchMovies(
        @Query("api_key") API_KEY: String, @Query("language") LANGUAGE: String, @Query("page") PAGE: Int, @Query(
            "query"
        ) QUERY: String
    ): Observable<TMDBResponse>


    @GET("movie/{movie_id}")
    fun getDetails(@Path("movie_id") MOVIE_ID: Int, @Query("api_key") API_KEY: String, @Query("language") LANGUAGE: String): Observable<TMDBDetailsResponse>

    @GET("movie/{movie_id}/credits")
    fun getCredits(@Path("movie_id") MOVIE_ID: Int, @Query("api_key") API_KEY: String): Observable<TMDBCreditsResponse>

    @GET("movie/{movie_id}/videos")
    fun getTrailers(@Path("movie_id") MOVIE_ID: Int, @Query("api_key") API_KEY: String, @Query("language") LANGUAGE: String): Observable<TMDBTrailerResponse>
}
