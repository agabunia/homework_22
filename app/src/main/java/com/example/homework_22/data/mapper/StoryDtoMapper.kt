package com.example.homework_22.data.mapper

import com.example.homework_22.data.model.StoryDto
import com.example.homework_22.domain.model.GetStory

fun StoryDto.toDomain(): GetStory {
    return GetStory(
        id = id,
        cover = cover,
        title = title
    )
}
