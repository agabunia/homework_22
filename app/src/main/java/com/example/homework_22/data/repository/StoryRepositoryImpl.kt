package com.example.homework_22.data.repository

import com.example.homework_22.data.common.HandleResponse
import com.example.homework_22.data.common.Resource
import com.example.homework_22.data.mapper.asResource
import com.example.homework_22.data.mapper.toDomain
import com.example.homework_22.data.service.StoryService
import com.example.homework_22.domain.model.GetStory
import com.example.homework_22.domain.repository.StoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StoryRepositoryImpl @Inject constructor(
    private val storyService: StoryService,
    private val handleResponse: HandleResponse
) : StoryRepository {
    override suspend fun getStory(): Flow<Resource<List<GetStory>>> {
        return handleResponse.safeApiCall {
            storyService.getStory()
        }.asResource { list ->
            list.map {
                it.toDomain()
            }
        }
    }
}
