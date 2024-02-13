package com.example.homework_22.data.service

import com.example.homework_22.data.model.PostDto
import com.example.homework_22.data.model.StoryDto
import retrofit2.Response
import retrofit2.http.GET

interface PostService {
    @GET("https://run.mocky.io/v3/25caefd7-7e7e-4178-a86f-e5cfee2d88a0")
    suspend fun getPost(): Response<List<PostDto>>
}
