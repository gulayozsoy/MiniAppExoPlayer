package com.example.miniappexoplayer.ui.secondpage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.miniappexoplayer.model.Movies

class SecondPageViewModel: ViewModel() {

    private val _actionMovieList = MutableLiveData<List<Movies>>()
    val actionMovieList: LiveData<List<Movies>> get() = _actionMovieList

    private val _dramaMovieList = MutableLiveData<List<Movies>>()
    val dramaMovieList: LiveData<List<Movies>> get() = _dramaMovieList

    private val _animationMovieList = MutableLiveData<List<Movies>>()
    val animationMovieList: LiveData<List<Movies>> get() = _animationMovieList

    private val _comedyMovieList = MutableLiveData<List<Movies>>()
    val comedyMovieList: LiveData<List<Movies>> get() = _comedyMovieList



    fun importData(allList: MutableList<Movies>) {
        _actionMovieList.value = allList.filter { it.genreIds?.contains(28) == true }
        _dramaMovieList.value = allList.filter { it.genreIds?.contains(18) == true }
        _animationMovieList.value = allList.filter { it.genreIds?.contains(16) == true }
        _comedyMovieList.value = allList.filter { it.genreIds?.contains(35) == true }
   }

}