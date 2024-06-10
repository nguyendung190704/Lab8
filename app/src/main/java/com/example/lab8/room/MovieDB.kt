package com.example.lab8.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
abstract class MovieDB : RoomDatabase() {

    abstract fun movieDao(): MovieDAO

    companion object {

        @Volatile
        private var INTANCE: MovieDB? = null

        fun getIntance(context: Context): MovieDB {
            synchronized(this){
                var intance = INTANCE
                if (intance == null){
                    intance = Room.databaseBuilder(
                        context.applicationContext,
                        MovieDB::class.java,
                        "movie_db1"
                    ).build()
                }
                return intance
            }

        }

    }

}