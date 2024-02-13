package com.example.homework_22.presentation.mapper

import com.example.homework_22.domain.model.GetPost
import com.example.homework_22.presentation.module.Post

fun GetPost.toPresenter(): Post {
    return Post(
        id = id,
        images = images,
        title = title,
        comments = comments,
        likes = likes,
        shareContent = shareContent,
        owner = owner.toPresenter()
    )
}

fun GetPost.GetOwnerInfo.toPresenter(): Post.OwnerInfo {
    return Post.OwnerInfo(
        firstName = firstName,
        lastName = lastName,
        profile = profile,
        postDate = postDate
    )
}