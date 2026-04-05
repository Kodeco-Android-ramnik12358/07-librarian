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

package com.raywenderlich.android.librarian.ui.reviews

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.raywenderlich.android.librarian.R
import com.raywenderlich.android.librarian.databinding.ItemBookReviewBinding
import com.raywenderlich.android.librarian.model.relations.BookReview

class BookReviewViewHolder(private val binding: ItemBookReviewBinding) : RecyclerView.ViewHolder(binding.root) {

  fun showData(bookReview: BookReview,
      onItemSelected: (BookReview) -> Unit,
      onItemLongTapped: (BookReview) -> Unit) {
    val (review, book) = bookReview

    binding.reviewTitle.text = binding.root.context.getString(R.string.review_title, book.name)
    binding.reviewRating.rating = review.rating.toFloat()
    binding.reviewNumberOfComments.text =
        binding.root.context.getString(R.string.number_of_reading_entries, review.entries.size)
    binding.reviewDescription.text = review.notes
    Glide.with(binding.root).load(review.imageUrl).into(binding.bookImage)

    binding.root.setOnClickListener { onItemSelected(bookReview) }
    binding.root.setOnLongClickListener {
      onItemLongTapped(bookReview)
      true
    }
  }
}