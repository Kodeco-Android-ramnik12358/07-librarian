package com.raywenderlich.android.librarian.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.raywenderlich.android.librarian.model.Book

@Dao
interface BookDao {

    @Query("SELECT * FROM books")
    fun getBooks(): List<Book>

    @Query("SELECT * FROM books WHERE id = :id")
    fun getBookById(id: String): Book

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addBook(book: Book)

    @Delete
    fun removeBook(book: Book)
}