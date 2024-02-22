package com.example.homework_22.domain.usecase.story

import com.example.homework_22.domain.repository.StoryRepository
import javax.inject.Inject

class GetStoryUseCase @Inject constructor(private val storyRepository: StoryRepository) {
    suspend operator fun invoke() = storyRepository.getStory()
}
