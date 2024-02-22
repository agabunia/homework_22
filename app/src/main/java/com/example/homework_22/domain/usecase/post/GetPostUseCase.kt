package com.example.homework_22.domain.usecase.post

import com.example.homework_22.domain.repository.PostRepository
import javax.inject.Inject

class GetPostUseCase @Inject constructor(private val postRepository: PostRepository) {
    suspend operator fun invoke() = postRepository.getPost()
}
