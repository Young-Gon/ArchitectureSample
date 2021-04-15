package com.example.architecturesample.ui.fragment.board

import androidx.lifecycle.ViewModel
import com.example.architecturesample.repository.BoardRepository

class BoardViewModel(
    private val repository: BoardRepository
) : ViewModel() {
    val postList = repository.getPostList()
}