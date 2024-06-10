package com.example.roomdb.repository

import com.example.lab8.room.MovieDB
import com.example.lab8.room.MovieEntity


class Repository(val movieDB: MovieDB) {
    suspend fun addMovieToRoom(movieEntity: MovieEntity){
        movieDB.movieDao().addMovie(movieEntity)
    }

    fun getAllMovie() = movieDB.movieDao().getALlMovie()

    suspend fun deleteMovieFromRoom(movieEntity: MovieEntity) {
        movieDB.movieDao().deleteMovie(movieEntity)
    }

    suspend fun updateMovie(movieEntity: MovieEntity){
        movieDB.movieDao().updateMovie(movieEntity)
    }
}