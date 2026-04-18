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

package com.raywenderlich.android.librarian.ui.books

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.raywenderlich.android.librarian.App
import com.raywenderlich.android.librarian.R
import com.raywenderlich.android.librarian.model.Book
import com.raywenderlich.android.librarian.model.relations.BookAndGenre
import com.raywenderlich.android.librarian.ui.addBook.AddBookActivity
import com.raywenderlich.android.librarian.ui.filter.Filter
import com.raywenderlich.android.librarian.ui.filter.FilterPickerDialogFragment
import com.raywenderlich.android.librarian.utils.createAndShowDialog
import com.raywenderlich.android.librarian.databinding.FragmentBooksBinding
import com.raywenderlich.android.librarian.ui.filter.ByGenre

private const val REQUEST_CODE_ADD_BOOK = 101

class BooksFragment : Fragment() {

  private var _binding: FragmentBooksBinding? = null
  private val binding get() = _binding!!
  private val adapter by lazy { BookAdapter(::onItemLongTapped) }
  private var filter: Filter? = null
  private val repository by lazy { App.repository }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
      savedInstanceState: Bundle?): View? {
    _binding = FragmentBooksBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initUi()
  }

  override fun onStart() {
    super.onStart()
    loadBooks()
  }

  private fun initUi() {
    binding.pullToRefresh.setOnRefreshListener {
      loadBooks()
    }

    binding.booksRecyclerView.adapter = adapter
    binding.booksRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    binding.addBook.setOnClickListener {
      startActivityForResult(AddBookActivity.getIntent(requireContext()), REQUEST_CODE_ADD_BOOK)
    }

    binding.filterBooks.setOnClickListener {
      val dialog = FilterPickerDialogFragment { filter ->
        this.filter = filter

        loadBooks()
      }

      dialog.show(requireFragmentManager(), null)
    }
  }

  private fun loadBooks() {
    binding.pullToRefresh.isRefreshing = true

    val books = when (val currentFilter = filter) {
      is ByGenre -> repository.getBooksByGenre(currentFilter.genreId)
      else -> repository.getBooks()
    }

    adapter.setData(books)
    binding.pullToRefresh.isRefreshing = false
  }

  private fun onItemLongTapped(book: Book) {
    createAndShowDialog(requireContext(),
        getString(R.string.delete_title),
        getString(R.string.delete_message, book.name),
        onPositiveAction = { removeBook(book) }
    )
  }

  private fun removeBook(book: Book) {
    // TODO remove book
    loadBooks()
  }
}