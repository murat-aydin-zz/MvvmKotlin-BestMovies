package com.murat.movielist.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.murat.movielist.db.dao.MovieDao
import com.murat.movielist.db.entitiy.MovieEntity

@Database(entities = arrayOf(MovieEntity::class), version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}