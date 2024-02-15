package com.example.homework_22.presentation.mapper

import com.example.homework_22.domain.model.GetStory
import com.example.homework_22.presentation.model.Story

fun GetStory.toPresenter(): Story {
    return Story(
        id = id,
        cover = cover,
        title = title
    )
}
