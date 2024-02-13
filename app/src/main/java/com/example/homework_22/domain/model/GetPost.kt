package com.example.homework_22.domain.model

data class GetPost(
    val id: Int,
    val images: List<String>?,
    val title: String,
    val comments: Int,
    val likes: Int,
    val shareContent: String,
    val owner: GetOwnerInfo
) {
    data class GetOwnerInfo(
        val firstName: String,
        val lastName: String,
        val profile: String?,
        val postDate: Int
    )
}
