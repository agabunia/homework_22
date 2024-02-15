package com.example.homework_22.presentation.state

import com.example.homework_22.presentation.model.Post
import com.example.homework_22.presentation.model.Story

data class MainState(
    val stories: List<Story>? = null,
    val posts: List<Post>? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
