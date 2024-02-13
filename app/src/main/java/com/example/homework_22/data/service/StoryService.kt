package com.example.homework_22.data.service

import com.example.homework_22.data.model.StoryDto
import retrofit2.Response
import retrofit2.http.GET

interface StoryService {
    @GET("https://run.mocky.io/v3/1e2c42be-fc82-4efb-9f3f-4ce4ce80743c")
    suspend fun getStory(): Response<List<StoryDto>>
}
