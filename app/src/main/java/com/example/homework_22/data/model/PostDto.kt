package com.example.homework_22.data.model

import com.squareup.moshi.Json

data class PostDto(
    @Json(name = "id")
    val id: Int,
    @Json(name = "images")
    val images: List<String>?,
    @Json(name = "title")
    val title: String,
    @Json(name = "comments")
    val comments: Int,
    @Json(name = "likes")
    val likes: Int,
    @Json(name = "share_content")
    val shareContent: String,
    @Json(name = "owner")
    val owner: OwnerInfoDto
) {
    data class OwnerInfoDto(
        @Json(name = "first_name")
        val firstName: String,
        @Json(name = "last_name")
        val lastName: String,
        @Json(name = "profile")
        val profile: String?,
        @Json(name = "post_date")
        val postDate: Int
    )
}
