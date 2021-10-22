package com.example.miniappexoplayer.repository

import com.example.miniappexoplayer.model.Genre
import com.example.miniappexoplayer.model.Movies
import com.example.miniappexoplayer.network.MoviesApiInterface
import com.example.miniappexoplayer.util.Constants.Constants.API_KEY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class MoviesRepositoryImpl(private val apiService: MoviesApiInterface) : MoviesRepository {
    override suspend fun getGenres(): List<Genre> {
        var result = listOf<Genre>()
        val response = apiService.getMovieGenres(API_KEY, "en-EN")
        if (response.isSuccessful) {
            response.body()?.let {
                withContext(Dispatchers.IO) { result = it.genres }
            }
        } else {
            throw HttpException(response)
        }

        return result
    }

    override suspend fun getMovies(apiKey: String, sortBy: String, includeAdult: Boolean,
                                   includeVideo: Boolean, page: Int, genreId: Int): List<Movies> {
        var result = listOf<Movies>()
        val response = apiService.getMovies(
           apiKey, sortBy, includeAdult, includeVideo, page, genreId )
        if (response.isSuccessful) {
            response.body()?.let {
                withContext(Dispatchers.IO) { result = it.result!! }
            }
        } else {
            throw HttpException(response)
        }
        return result
    }
}
