package com.raywenderlich.android.librarian.repository

import com.raywenderlich.android.librarian.database.dao.BookDao
import com.raywenderlich.android.librarian.database.dao.GenreDao
import com.raywenderlich.android.librarian.database.dao.ReadingListDao
import com.raywenderlich.android.librarian.database.dao.ReviewDao
import com.raywenderlich.android.librarian.model.Book
import com.raywenderlich.android.librarian.model.Genre
import com.raywenderlich.android.librarian.model.ReadingList
import com.raywenderlich.android.librarian.model.Review
import com.raywenderlich.android.librarian.model.relations.BookAndGenre
import com.raywenderlich.android.librarian.model.relations.BookReview
import com.raywenderlich.android.librarian.model.relations.ReadingListsWithBooks

class LibrarianRepositoryImpl(
    private val bookDao: BookDao,
    private val genreDao: GenreDao,
    private val readingListDao: ReadingListDao,
    private val reviewDao: ReviewDao
) : LibrarianRepository {
    // region Books
    override fun getBooks(): List<BookAndGenre> {
        return bookDao.getBooks()
    }

    override fun getBookById(id: String): Book {
        return bookDao.getBookById(id)
    }

    override fun addBook(book: Book) {
        bookDao.addBook(book)
    }

    override fun removeBook(book: Book) {
        bookDao.removeBook(book)
    }
    // endregion

    // region Genres
    override fun getGenres(): List<Genre> {
        return genreDao.getGenres()
    }

    override fun getGenreById(genreId: String): Genre {
        return genreDao.getGenreById(genreId)
    }

    override fun addGenre(genre: Genre) {
        genreDao.addGenre(genre)
    }
    // endregion

    // region Reviews
    override fun getReviews(): List<BookReview> {
        val reviews = reviewDao.getReviews()
        val bookReviews = reviews.map { BookReview(it, bookDao.getBookById(it.bookId)) }

        return bookReviews
    }

    override fun getReviewById(id: String): BookReview {
        val review = reviewDao.getReviewById(id)
        val bookReview = BookReview(review, bookDao.getBookById(review.bookId))

        return bookReview
    }

    override fun addReview(review: Review) {
        reviewDao.addReview(review)
    }

    override fun updateReview(review: Review) {
        reviewDao.updateReview(review)
    }

    override fun removeReview(review: Review) {
        reviewDao.removeReview(review)
    }
    // endregion

    // region ReadingLists
    override fun getReadingLists(): List<ReadingListsWithBooks> {
        val readingLists = readingListDao.getReadingLists()
        val readingListsWithBooks =
            readingLists.map { ReadingListsWithBooks(it.id, it.name, emptyList()) }

        return readingListsWithBooks
    }

    override fun addReadingList(readingList: ReadingList) {
        readingListDao.addReadingList(readingList)
    }

    override fun removeReadingList(readingList: ReadingList) {
        readingListDao.removeReadingList(readingList)
    }
    // endregion

    // region Relationships
    override fun getBooksByGenre(genreId: String): List<BookAndGenre> {
        genreDao.getBooksByGenre(genreId).let { booksByGenre ->
            val books = booksByGenre.books ?: return emptyList()

            return books.map { BookAndGenre(it, booksByGenre.genre) }
        }
    }
    // endregion
}