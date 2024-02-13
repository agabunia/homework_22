package com.example.homework_22.data.mapper

import com.example.homework_22.data.model.PostDto
import com.example.homework_22.domain.model.GetPost

fun PostDto.toDomain(): GetPost {
    return GetPost(
        id = id,
        images = images,
        title = title,
        comments = comments,
        likes = likes,
        shareContent = shareContent,
        owner = owner.toDomain()
    )
}

fun PostDto.OwnerInfoDto.toDomain(): GetPost.GetOwnerInfo {
    return GetPost.GetOwnerInfo(
        firstName = firstName,
        lastName = lastName,
        profile = profile,
        postDate = postDate
    )
}
