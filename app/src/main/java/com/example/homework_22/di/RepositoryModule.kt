package com.example.homework_22.di

import com.example.homework_22.data.common.HandleResponse
import com.example.homework_22.data.repository.DetailedPostRepositoryImpl
import com.example.homework_22.data.repository.PostRepositoryImpl
import com.example.homework_22.data.repository.StoryRepositoryImpl
import com.example.homework_22.data.service.DetailedPostService
import com.example.homework_22.data.service.PostService
import com.example.homework_22.data.service.StoryService
import com.example.homework_22.domain.repository.DetailedPostRepository
import com.example.homework_22.domain.repository.PostRepository
import com.example.homework_22.domain.repository.StoryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideStoryRepository(
        handleResponse: HandleResponse, storyService: StoryService
    ): StoryRepository {
        return StoryRepositoryImpl(handleResponse = handleResponse, storyService = storyService)
    }

    @Provides
    @Singleton
    fun providePostRepository(
        handleResponse: HandleResponse, postService: PostService
    ): PostRepository {
        return PostRepositoryImpl(handleResponse = handleResponse, postService = postService)
    }

    @Provides
    @Singleton
    fun provideDetailedPostRepository(
        handleResponse: HandleResponse, detailedPostService: DetailedPostService
    ): DetailedPostRepository {
        return DetailedPostRepositoryImpl(
            handleResponse = handleResponse,
            detailedPostService = detailedPostService
        )
    }

}