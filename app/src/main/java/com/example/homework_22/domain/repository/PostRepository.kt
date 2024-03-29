package com.example.homework_22.domain.repository

import com.example.homework_22.data.common.Resource
import com.example.homework_22.domain.model.GetPost
import kotlinx.coroutines.flow.Flow

interface PostRepository {
    suspend fun getPost(): Flow<Resource<List<GetPost>>>
}
