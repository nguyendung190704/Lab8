package com.example.lab8.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDAO {

    @Insert
    suspend fun addMovie(movieEntity: MovieEntity)

    @Query("SELECT * FROM MovieEntity")
    fun getALlMovie(): Flow<List<MovieEntity>>

    @Delete
    suspend fun deleteMovie(movieEntity: MovieEntity)

    @Update
    suspend fun updateMovie(movieEntity: MovieEntity)
}