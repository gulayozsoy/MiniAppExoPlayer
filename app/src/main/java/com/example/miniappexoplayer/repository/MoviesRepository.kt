package com.example.miniappexoplayer.repository

import com.example.miniappexoplayer.model.Genre
import com.example.miniappexoplayer.model.Movies

interface MoviesRepository {
    suspend fun getGenres(): List<Genre>

    suspend fun getMovies(apiKey: String, sortBy: String, includeAdult: Boolean,
                          includeVideo: Boolean, page: Int, genreId: Int): List<Movies>

}