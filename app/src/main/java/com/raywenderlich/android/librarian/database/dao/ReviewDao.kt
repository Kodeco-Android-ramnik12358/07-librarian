package com.raywenderlich.android.librarian.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.raywenderlich.android.librarian.model.Review
import com.raywenderlich.android.librarian.model.relations.BookReview

@Dao
interface ReviewDao {

    @Query("SELECT * FROM reviews")
    fun getReviews(): List<BookReview>

    @Query("SELECT * FROM reviews WHERE id = :id")
    fun getReviewById(id: String): BookReview

    @Query("SELECT * FROM reviews WHERE rating >= :rating")
    fun getReviewsByRating(rating: Int): List<BookReview>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addReview(review: Review)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun updateReview(review: Review)

    @Delete
    fun removeReview(review: Review)
}