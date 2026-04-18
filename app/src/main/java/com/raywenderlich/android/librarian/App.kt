/*
 * Copyright (c) 2020 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * This project and source code may use libraries or frameworks that are
 * released under various Open-Source licenses. Use of those libraries and
 * frameworks are governed by their own individual licenses.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.raywenderlich.android.librarian

import android.app.Application
import com.raywenderlich.android.librarian.database.LibrarianDatabase
import com.raywenderlich.android.librarian.model.Genre
import com.raywenderlich.android.librarian.repository.LibrarianRepository
import com.raywenderlich.android.librarian.repository.LibrarianRepositoryImpl

class App : Application() {

    companion object {
        private lateinit var instance: App

        private val database: LibrarianDatabase by lazy {
            LibrarianDatabase.buildDatabase(instance)
        }

        val repository: LibrarianRepository by lazy {
            LibrarianRepositoryImpl(
                database.bookDao(),
                database.genreDao(),
                database.readingListDao(),
                database.reviewDao()
            )
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        prepopulateDatabase()
    }

    private fun prepopulateDatabase() {
        if (!repository.getGenres().isEmpty()) {
            return
        }

        val genre1 = Genre(name = "Action")
        val genre2 = Genre(name = "Adventure")
        val genre3 = Genre(name = "Classic")
        val genre4 = Genre(name = "Mystery")
        val genre5 = Genre(name = "Fantasy")
        val genre6 = Genre(name = "Sci-Fi")
        val genre7 = Genre(name = "History")
        val genre8 = Genre(name = "Horror")
        val genre9 = Genre(name = "Romance")
        val genre10 = Genre(name = "Short Story")
        val genre11 = Genre(name = "Biography")
        val genre12 = Genre(name = "Poetry")
        val genre13 = Genre(name = "Self-Help")
        val genre14 = Genre(name = "Young novel")

        repository.addGenre(genre1)
        repository.addGenre(genre2)
        repository.addGenre(genre3)
        repository.addGenre(genre4)
        repository.addGenre(genre5)
        repository.addGenre(genre6)
        repository.addGenre(genre7)
        repository.addGenre(genre8)
        repository.addGenre(genre9)
        repository.addGenre(genre10)
        repository.addGenre(genre11)
        repository.addGenre(genre12)
        repository.addGenre(genre13)
        repository.addGenre(genre14)
    }
}