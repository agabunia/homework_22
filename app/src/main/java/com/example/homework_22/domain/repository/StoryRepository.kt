package com.example.homework_22.domain.repository

import com.example.homework_22.data.common.Resource
import com.example.homework_22.domain.model.GetStory
import kotlinx.coroutines.flow.Flow

interface StoryRepository {
    suspend fun getStory(): Flow<Resource<List<GetStory>>>
}
