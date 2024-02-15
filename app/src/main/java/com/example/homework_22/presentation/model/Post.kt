package com.example.homework_22.presentation.model

data class Post(
    val id: Int,
    val images: List<String>,
    val title: String,
    val comments: Int,
    val likes: Int,
    val shareContent: String,
    val owner: OwnerInfo
) {
    data class OwnerInfo(
        val firstName: String,
        val lastName: String,
        val profile: String,
        val postDate: String
    )
}
