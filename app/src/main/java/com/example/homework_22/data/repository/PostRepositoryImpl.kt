package com.example.homework_22.data.repository

import com.example.homework_22.data.common.HandleResponse
import com.example.homework_22.data.common.Resource
import com.example.homework_22.data.mapper.asResource
import com.example.homework_22.data.mapper.toDomain
import com.example.homework_22.data.service.PostService
import com.example.homework_22.domain.model.GetPost
import com.example.homework_22.domain.repository.PostRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val postService: PostService,
    private val handleResponse: HandleResponse
) : PostRepository {
    override suspend fun getPost(): Flow<Resource<List<GetPost>>> {
        return handleResponse.safeApiCall {
            postService.getPost()
        }.asResource { list ->
            list.map {
                it.toDomain()
            }
        }
    }
}