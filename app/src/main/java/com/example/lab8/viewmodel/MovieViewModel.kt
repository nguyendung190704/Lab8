package com.example.lab8.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lab8.room.MovieEntity
import com.example.roomdb.repository.Repository
import kotlinx.coroutines.launch

class MovieViewModel(val repository: Repository): ViewModel() {

    fun addMovie(movie: MovieEntity) {
        viewModelScope.launch {
            repository.addMovieToRoom(movie)
        }
    }

    val movies = repository.getAllMovie()

    fun deleteMovie(movie: MovieEntity) {
        viewModelScope.launch {
            repository.deleteMovieFromRoom(movie)
        }
    }

    fun updateMovie(movie: MovieEntity) {
        viewModelScope.launch {
            repository.updateMovie(movie)
        }
    }
}