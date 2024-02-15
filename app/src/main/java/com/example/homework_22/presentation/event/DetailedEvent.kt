package com.example.homework_22.presentation.event

sealed class DetailedEvent {
    object FetchPost: DetailedEvent()
    object ResetErrorMessage: DetailedEvent()
}