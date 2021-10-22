package com.example.miniappexoplayer.network

import com.example.miniappexoplayer.model.GenreResponse
import com.example.miniappexoplayer.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query



interface MoviesApiInterface {

    //https://api.themoviedb.org/3/genre/movie/list?api_key=3bb3e67969473d0cb4a48a0dd61af747&language=en-US

    @GET("genre/movie/list")
    suspend fun getMovieGenres(@Query("api_key") apiKey: String,
                               @Query("language") language: String) : Response<GenreResponse>

    //https://api.themoviedb.org/3/discover/movie?api_key=3bb3e67969473d0cb4a48a0dd61af747&sort_by=popularity.desc&include_adult=false&include_video=false&page=1&with_genres=28
    @GET("discover/movie")
    suspend fun getMovies(@Query("api_key") apiKey: String,
                          @Query("sort_by") sortBy: String,
                          @Query("include_adult") includeAdult: Boolean,
                          @Query("include_video") includeVideo: Boolean,
                          @Query("page") page: Int,
                          @Query("with_genres") genreId: Int):  Response<MovieResponse>
}