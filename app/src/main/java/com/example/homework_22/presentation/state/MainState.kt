package com.example.homework_22.presentation.state

import com.example.homework_22.presentation.module.Post
import com.example.homework_22.presentation.module.Story

data class MainState(
    val stories: List<Story>? = null,
    val posts: List<Post>? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
