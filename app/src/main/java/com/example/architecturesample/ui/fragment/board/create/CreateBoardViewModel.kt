package com.example.architecturesample.ui.fragment.board.create

import androidx.lifecycle.*
import com.example.architecturesample.model.network.response.PostData
import com.example.architecturesample.repository.BoardRepository
import com.example.architecturesample.util.Event
import com.example.architecturesample.util.NetworkState
import kotlinx.coroutines.launch
import timber.log.Timber
import java.time.LocalDateTime

class CreateBoardViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val repository: BoardRepository,
) : ViewModel() {

    val post = savedStateHandle.getLiveData("postId", -1).switchMap { postId ->
        Timber.i("postId=$postId")
        repository.getPost(postId)
    }.map { post ->
        Timber.i("post=${post?.toString()}")
        title.value = post?.title ?: ""
        content.value = post?.content ?: ""
        post
    }

    val title = MutableLiveData("")
    val content = MutableLiveData("")

    private val _confirmButtonResult = MutableLiveData<Event<NetworkState<String>>>()
    val confirmButtonResult: LiveData<Event<NetworkState<String>>> = _confirmButtonResult

    fun onClickConfirm() {
        val title = title.value
        if (title == null || title.isEmpty()) {
            _confirmButtonResult.value = Event(NetworkState.error("제목을 입력해 주세요", null))
            return
        }
        val content = content.value
        if (content == null || content.isEmpty()) {
            _confirmButtonResult.value = Event(NetworkState.error("내용을 입력해 주세요", null))
            return
        }
        Timber.i("title = $title, content=$content")
        viewModelScope.launch {
            repository.savePost(
                post.value?.copy(
                    title = title,
                    content = content,
                    modifiedAt = LocalDateTime.now()
                ) ?: PostData(
                    title = title,
                    content = content
                )
            )

            _confirmButtonResult.value =
                Event(NetworkState.success(if (post.value == null) "추가 하였습니다" else "수정 하였습니다"))
        }
    }
}
