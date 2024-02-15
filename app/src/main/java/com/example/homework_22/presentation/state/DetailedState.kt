package com.example.homework_22.presentation.state

import com.example.homework_22.presentation.model.Post

data class DetailedState(
    val post: Post? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)