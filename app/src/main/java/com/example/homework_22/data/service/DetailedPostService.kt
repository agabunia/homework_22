package com.example.homework_22.data.service

import com.example.homework_22.data.model.PostDto
import retrofit2.Response
import retrofit2.http.GET

interface DetailedPostService {
    @GET("https://run.mocky.io/v3/d02b76bb-095d-45fa-90e1-dc4733d1f247?id=1")
    suspend fun getDetailedPost(): Response<PostDto>
}