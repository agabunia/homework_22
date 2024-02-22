package com.example.homework_22.presentation.adapter.post

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.homework_22.databinding.PostLayoutBinding
import com.example.homework_22.presentation.adapter.custom_layout_manager.CustomLayoutManager
import com.example.homework_22.presentation.extension.loadImage
import com.example.homework_22.presentation.model.Post

class PostRecyclerAdapter : ListAdapter<Post, PostRecyclerAdapter.PostViewHolder>(PostDiffUtil()) {

    class PostDiffUtil : DiffUtil.ItemCallback<Post>() {
        override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        return PostViewHolder(PostLayoutBinding.inflate(inflate, parent, false))
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind()
    }

    inner class PostViewHolder(private val binding: PostLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private lateinit var post: Post
        private lateinit var imagesRecyclerViewAdapter: ImagesRecyclerViewAdapter

        fun bind() {
            post = currentList[adapterPosition]

            binding.apply {
                tvUserFirstName.text = post.owner.firstName
                tvUserLastName.text = post.owner.lastName

                ivAuthorProfileImage.loadImage(post.owner.profile, CircleCrop())

                tvPostTime.text = post.owner.postDate
                tvTitle.text = post.title
                tvCommentNumber.text = post.comments.toString()
                tvLikeNumber.text = post.likes.toString()

                imagesRecyclerViewAdapter = ImagesRecyclerViewAdapter(post.images)
                rvImages.adapter = imagesRecyclerViewAdapter
                rvImages.layoutManager = CustomLayoutManager()
                rvImages.requestLayout()
//                rvImages.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

            }
        }
    }
}
