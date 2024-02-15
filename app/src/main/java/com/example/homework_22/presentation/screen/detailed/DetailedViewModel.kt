package com.example.homework_22.presentation.screen.detailed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework_22.data.common.Resource
import com.example.homework_22.domain.repository.DetailedPostRepository
import com.example.homework_22.presentation.event.DetailedEvent
import com.example.homework_22.presentation.mapper.toPresenter
import com.example.homework_22.presentation.state.DetailedState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailedViewModel @Inject constructor(
    private val detailedPostRepository: DetailedPostRepository
) : ViewModel() {

    private val _detailedState = MutableStateFlow(DetailedState())
    val detailedState: SharedFlow<DetailedState> = _detailedState.asStateFlow()

    fun onEvent(event: DetailedEvent) {
        when (event) {
            is DetailedEvent.FetchPost -> fetchPost()
            is DetailedEvent.ResetErrorMessage -> errorMessage(message = null)
        }
    }

    private fun fetchPost() {
        viewModelScope.launch {
            detailedPostRepository.getDetailedPost().collect {
                when (it) {
                    is Resource.Success -> {
                        _detailedState.update { currentState ->
                            currentState.copy(post = it.data.toPresenter())
                        }
                    }

                    is Resource.Error -> errorMessage(it.errorMessage)

                    is Resource.Loading -> {
                        _detailedState.update { currentState ->
                            currentState.copy(isLoading = it.loading)
                        }
                    }
                }
            }
        }
    }

    private fun errorMessage(message: String?) {
        _detailedState.update { currentState ->
            currentState.copy(errorMessage = message)
        }
    }

}