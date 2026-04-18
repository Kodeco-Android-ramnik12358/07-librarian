package com.raywenderlich.android.librarian.repository

import com.raywenderlich.android.librarian.model.Book
import com.raywenderlich.android.librarian.model.Genre
import com.raywenderlich.android.librarian.model.ReadingList
import com.raywenderlich.android.librarian.model.Review
import com.raywenderlich.android.librarian.model.relations.BookAndGenre
import com.raywenderlich.android.librarian.model.relations.BookReview
import com.raywenderlich.android.librarian.model.relations.ReadingListsWithBooks

interface LibrarianRepository {

    // region Books
    fun getBooks(): List<BookAndGenre>

    fun getBookById(id: String): Book

    fun addBook(book: Book)

    fun removeBook(book: Book)
    // endregion

    // region Genres
    fun getGenres(): List<Genre>

    fun getGenreById(genreId: String): Genre

    fun addGenre(genre: Genre)
    // endregion

    // region Reviews
    fun getReviews(): List<BookReview>

    fun getReviewById(id: String): BookReview

    fun addReview(review: Review)

    fun updateReview(review: Review)

    fun removeReview(review: Review)
    // endregion

    // region ReadingLists
    fun getReadingLists(): List<ReadingListsWithBooks>

    fun addReadingList(readingList: ReadingList)

    fun removeReadingList(readingList: ReadingList)
    // endregion
}