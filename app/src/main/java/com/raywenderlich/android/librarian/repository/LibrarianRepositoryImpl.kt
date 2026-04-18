package com.raywenderlich.android.librarian.repository

import com.raywenderlich.android.librarian.database.dao.BookDao
import com.raywenderlich.android.librarian.database.dao.GenreDao
import com.raywenderlich.android.librarian.database.dao.ReadingListDao
import com.raywenderlich.android.librarian.database.dao.ReviewDao
import com.raywenderlich.android.librarian.model.Book
import com.raywenderlich.android.librarian.model.Genre
import com.raywenderlich.android.librarian.model.relations.BookAndGenre

class LibrarianRepositoryImpl(
    private val bookDao: BookDao,
    private val genreDao: GenreDao,
    private val readingListDao: ReadingListDao,
    private val reviewDao: ReviewDao
): LibrarianRepository {
    override fun getBooks(): List<BookAndGenre> {
        return bookDao.getBooks().map {
            BookAndGenre(it, genreDao.getGenreById(it.genreId))
        }
    }

    override fun addBook(book: Book) {
        bookDao.addBook(book)
    }

    override fun getGenres(): List<Genre> {
        return genreDao.getGenres()
    }

    override fun getGenreById(genreId: String): Genre {
       return genreDao.getGenreById(genreId)
    }

    override fun addGenre(genre: Genre) {
        genreDao.addGenre(genre)
    }
}