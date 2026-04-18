package com.raywenderlich.android.librarian.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.raywenderlich.android.librarian.model.Genre

@Dao
interface GenreDao {

    @Query("SELECT * FROM genres")
    fun getGenres(): List<Genre>

    @Query("SELECT * FROM genres WHERE id = :genreId")
    fun getGenreById(genreId: String): Genre

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addGenre(genre: Genre)
}