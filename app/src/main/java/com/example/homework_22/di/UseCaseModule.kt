package com.example.homework_22.di

import com.example.homework_22.domain.repository.PostRepository
import com.example.homework_22.domain.repository.StoryRepository
import com.example.homework_22.domain.usecase.post.GetPostUseCase
import com.example.homework_22.domain.usecase.story.GetStoryUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun provideGetPostUseCase(postRepository: PostRepository): GetPostUseCase {
        return GetPostUseCase(postRepository = postRepository)
    }

    @Singleton
    @Provides
    fun provideGetStoryUseCase(storyRepository: StoryRepository): GetStoryUseCase {
        return GetStoryUseCase(storyRepository = storyRepository)
    }

}