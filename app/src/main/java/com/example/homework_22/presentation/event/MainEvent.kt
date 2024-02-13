package com.example.homework_22.presentation.event

sealed class MainEvent {
    object FetchStories : MainEvent()
    object FetchPosts: MainEvent()
    object ResetErrorMessage: MainEvent()
}
