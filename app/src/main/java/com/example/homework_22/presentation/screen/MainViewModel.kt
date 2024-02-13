package com.example.homework_22.presentation.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework_22.data.common.Resource
import com.example.homework_22.domain.repository.PostRepository
import com.example.homework_22.domain.repository.StoryRepository
import com.example.homework_22.presentation.event.MainEvent
import com.example.homework_22.presentation.mapper.toPresenter
import com.example.homework_22.presentation.state.MainState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val storyRepository: StoryRepository,
    private val postRepository: PostRepository

) : ViewModel() {

    private val _mainState = MutableStateFlow(MainState())
    val mainState: SharedFlow<MainState> = _mainState.asStateFlow()

    fun onEvent(event: MainEvent) {
        when (event) {
            is MainEvent.FetchStories -> fetchStories()
            is MainEvent.FetchPosts -> fetchPosts()
            is MainEvent.ResetErrorMessage -> errorMessage(message = null)
        }
    }

    private fun fetchStories() {
        viewModelScope.launch {
            storyRepository.getStory().collect {
                when (it) {
                    is Resource.Success -> {
                        _mainState.update { currentState ->
                            currentState.copy(stories = it.data.map { domainModel ->
                                domainModel.toPresenter()
                            })
                        }
                    }

                    is Resource.Error -> errorMessage(it.errorMessage)

                    is Resource.Loading -> {
                        _mainState.update { currentState ->
                            currentState.copy(isLoading = it.loading)
                        }
                    }
                }
            }
        }
    }

    private fun fetchPosts() {
        viewModelScope.launch {
            postRepository.getPost().collect {
                when (it) {
                    is Resource.Success -> {
                        _mainState.update { currentState ->
                            currentState.copy(posts = it.data.map { domainModel ->
                                domainModel.toPresenter()
                            })
                        }
                    }

                    is Resource.Error -> errorMessage(it.errorMessage)

                    is Resource.Loading -> {
                        _mainState.update { currentState ->
                            currentState.copy(isLoading = it.loading)
                        }
                    }
                }
            }
        }
    }

    private fun errorMessage(message: String?) {
        _mainState.update { currentState ->
            currentState.copy(errorMessage = message)
        }
    }

}
