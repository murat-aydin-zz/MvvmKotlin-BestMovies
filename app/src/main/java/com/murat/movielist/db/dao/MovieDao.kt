package com.murat.movielist.db.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import com.murat.movielist.db.entitiy.MovieEntity

@Dao
interface MovieDao {

    @Query("SELECT * FROM Movie")
    fun getMovies(): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM Movie WHERE id = :movieId")
    fun getMovie(movieId: Int): MovieEntity

    @Insert(onConflict = REPLACE)
    fun insertMovie(movie: MovieEntity)

    @Query("DELETE FROM Movie WHERE id = :movieId")
    fun deleteMovie(movieId: Int)


}