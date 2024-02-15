package com.example.homework_22.data.repository

import com.example.homework_22.data.common.HandleResponse
import com.example.homework_22.data.common.Resource
import com.example.homework_22.data.mapper.asResource
import com.example.homework_22.data.mapper.toDomain
import com.example.homework_22.data.service.DetailedPostService
import com.example.homework_22.domain.model.GetPost
import com.example.homework_22.domain.repository.DetailedPostRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DetailedPostRepositoryImpl @Inject constructor(
    private val detailedPostService: DetailedPostService,
    private val handleResponse: HandleResponse
) : DetailedPostRepository {
    override suspend fun getDetailedPost(): Flow<Resource<GetPost>> {
        return handleResponse.safeApiCall {
            detailedPostService.getDetailedPost()
        }.asResource {
            it.toDomain()
        }
    }
}