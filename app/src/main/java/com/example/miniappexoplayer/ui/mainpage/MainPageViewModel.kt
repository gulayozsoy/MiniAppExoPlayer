package com.example.miniappexoplayer.ui.mainpage

import androidx.lifecycle.*
import com.example.miniappexoplayer.model.Genre
import com.example.miniappexoplayer.model.Movies
import com.example.miniappexoplayer.repository.MoviesRepository
import com.example.miniappexoplayer.util.Constants.Constants.API_KEY
import com.example.miniappexoplayer.util.Event
import kotlinx.coroutines.*

class MainPageViewModel(private val repository: MoviesRepository): ViewModel() {

    private val _viewPagerList = MutableLiveData<List<Movies>>()
    val viewpagerList: LiveData<List<Movies>> get()= _viewPagerList

    private val _actionMovieList = MutableLiveData<List<Movies>>()
    val actionMovieList: LiveData<List<Movies>> get() = _actionMovieList

    private val _dramaMovieList = MutableLiveData<List<Movies>>()
    val dramaMovieList: LiveData<List<Movies>> get() = _dramaMovieList

    private val _animationMovieList = MutableLiveData<List<Movies>>()
    val animationMovieList: LiveData<List<Movies>> get() = _animationMovieList

    private val _comedyMovieList = MutableLiveData<List<Movies>>()
    val comedyMovieList: LiveData<List<Movies>> get() = _comedyMovieList


    private val _genreList = MutableLiveData<List<Genre>>()
    val genreList: LiveData<List<Genre>> get() = _genreList

    val getDataAsIntent = MutableLiveData<Event<List<Movies>>>()

    fun getGenres() {
        var list = emptyList<Genre>()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                 list = repository.getGenres()
                if(list.isNotEmpty()) {
                    _genreList.postValue(list)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                 val result1  =
                     withContext(Dispatchers.Default) {
                         repository.getMovies(API_KEY, "popularity.desc", false, false, 1, 28)
                     }
                _actionMovieList.postValue(result1)
                _viewPagerList.postValue(result1.sortedBy { it.popularity }.take(10))  //First 10 most popular movies on viewpager!

                val result2 = withContext(Dispatchers.Default) {
                    repository.getMovies(API_KEY, "popularity.desc", false, false, 1, 18)
                }
                _dramaMovieList.postValue(result2)

                val result3 = withContext(Dispatchers.Default) {
                    repository.getMovies(API_KEY, "popularity.desc", false, false, 1, 16)
                }
                _animationMovieList.postValue(result3)

                val result4 = withContext(Dispatchers.Default) {
                    repository.getMovies(API_KEY, "popularity.desc", false, false, 1, 35)
                }
                _comedyMovieList.postValue(result4)

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getAllData() {
        val list= mutableListOf<Movies>()
        list.addAll(_actionMovieList.value as MutableList<Movies>)
        list.addAll(_dramaMovieList.value as MutableList<Movies>)
        list.addAll(_animationMovieList.value as MutableList<Movies>)
        list.addAll(_comedyMovieList.value as MutableList<Movies>)

        getDataAsIntent.value = Event(list)
    }
}